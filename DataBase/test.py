token_auth = "4abdc50c-9394-4cde-b9ee-df310e965108"

import pandas
import requests

a = requests.get(
        ('https://api.sncf.com/v1/coverage/sncf/stop_areas/stop_area:SNCF:87686006/departures'),
        auth=(token_auth, '')).json()

print(a["departures"][2])