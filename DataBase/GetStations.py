token_auth = "4abdc50c-9394-4cde-b9ee-df310e965108"

import pandas
import requests

def page_gares(numero_page) :
    return requests.get(
        ('https://api.sncf.com/v1/coverage/sncf/stop_areas?start_page={}').format(numero_page),
        auth=(token_auth, ''))

page_initiale = page_gares(0)
item_per_page = page_initiale.json()['pagination']['items_per_page']
total_items = page_initiale.json()['pagination']['total_result']
dfs = []

print_done = {}

for page in range(int(total_items/item_per_page)+1) :
    stations_page = page_gares(page)
    ensemble_stations = stations_page.json()

    if 'stop_areas' not in ensemble_stations:
        continue

    for station in ensemble_stations['stop_areas']:
        station['lat'] = station['coord']['lat']
        station["lon"]  = station['coord']['lon']

        if 'administrative_regions' in station.keys() :
            for var_api, var_df in zip(['insee','name','label','id','zip_code'],
                                       ['insee','region','label_region','id_region','zip_code']):
                try:
                    station[var_df] = station['administrative_regions'][0][var_api]
                except KeyError:
                    if var_api not in print_done:
                        print_done[var_api] = var_api

        # # Modifier les codes postaux pour avoir que le premier code postale
        # if 'zip_code' in station:
        #     station['zip_code'] = station['zip_code'].split(';')[0]
        # Modifier les codes postaux de ";" par un ":"
        if 'zip_code' in station:
            station['zip_code'] = ':'.join(station['zip_code'].split(';'))

        [station.pop(k,None) for k in ['coord','links','administrative_regions', 'type', 'codes']]

    stations = ensemble_stations['stop_areas']
    try:
        dp = pandas.DataFrame(stations)
    except Exception as e:
        raise Exception("Problème de données\n{0}".format(stations)) from e
    dfs.append(dp)
    df = pandas.concat(dfs)

df.to_csv("./ensemble_gares.csv")