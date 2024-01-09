import csv 

with open('ensemble_gares.csv', 'r') as csv_file:
    csv_reader = csv.DictReader(csv_file,delimiter=',')
    station_list = []
    with open('CreateStations.sql', 'w') as new_file:
        csv_writer = csv.writer(new_file, delimiter='\n')
        csv_writer.writerow(['USE SIA;'])  # Ajout de l'instruction USE SIA;

        for station in csv_reader:
            create = "INSERT INTO `Station` (`StationID`, `StationName`, `City`, `ZipCode`, `Lat`, `Lon`) VALUES ('{}', '{}', '{}', '{}', '{}', '{}');".format(station['id'], station['name'].replace("'","''"), station['region'].replace("'","''"), station['zip_code'],station['lat'],station['lon'])
            if station['id'] in station_list:
                continue

            csv_writer.writerow([create])
            station_list.append(station['id'])

