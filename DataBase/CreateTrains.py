import csv
import random
from datetime import datetime, timedelta

# Read the CSV file
csv_file_path = 'ensemble_gares.csv'
with open(csv_file_path, 'r', encoding='utf-8') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    stations = [row['id'] for row in csv_reader if row['id'] != '']

# Function to generate random datetime within a week
def generate_random_datetime(base_datetime):
    days_offset = random.randint(0, 6)
    return base_datetime + timedelta(days=days_offset, hours=random.randint(0, 23), minutes=random.randint(0, 59))

# Function to generate random inputs for the Train table
def generate_random_train_data(train_id):
    train_name = f'Train{train_id}'
    departure_station = random.choice(stations)
    arrival_station = random.choice(stations)
    
    departure_time = generate_random_datetime(datetime.now())
    arrival_time = generate_random_datetime(departure_time)
    
    num_tickets = random.randint(50, 200)
    travel_class = random.choice(['First Class', 'Second Class'])

    return f"INSERT INTO Train VALUES ({train_id}, '{train_name}', '{departure_station}', '{arrival_station}', " \
           f"'{departure_time}', '{arrival_time}', {num_tickets}, '{travel_class}');"

# Generate random train data for a week
num_trains_per_day = 100
num_days = 10
output_file_path = 'CreateTrains.sql'

with open(output_file_path, 'w') as output_file:
    generated_train_ids = set()
    for day in range(num_days):
        for train_id in range(1, num_trains_per_day + 1):
            while train_id in generated_train_ids:
                train_id += 1
            sql_command = generate_random_train_data(train_id)
            output_file.write(sql_command + '\n')
            generated_train_ids.add(train_id)

print(f"SQL commands generated and saved in {output_file_path}")
