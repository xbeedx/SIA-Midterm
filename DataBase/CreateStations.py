# https://www.data.gouv.fr/fr/datasets/liste-des-gares/

import csv

with open('liste-des-gares.csv', 'r') as csv_file:
    csv_reader = csv.DictReader(csv_file,delimiter=';')
    station_list = []

    with open('CreateStations.sql', 'w') as new_file:
        csv_writer = csv.writer(new_file, delimiter='\n')
        for station in csv_reader:
            create = "INSERT INTO `Station` (`StationID`, `StationName`, `City`, `Department`) VALUES ('{}', '{}', '{}', '{}');".format(station['code_uic'], station['libelle'].replace("'","''"), station['commune'].replace("'","''"), station['departement'].replace("'","''"))
            if station['code_uic'] in station_list:
                continue
            csv_writer.writerow([create])
            station_list.append(station['code_uic'])