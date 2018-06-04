from datetime import datetime, timedelta
from random import randrange

# User Table

# # CONSTANTS
NUM_USERS = 200
MIN_DATE_JOINED = datetime.strptime('2017-01-01 00:00:00', '%Y-%m-%d %H:%M:%S')
OUTPUT_FILENAME = './user_table_data.sql'
FNAME_FILENAME = './dummy_data/names.txt'
LNAME_FILENAME = './dummy_data/lastnames.txt'

# # uids
uids = range(NUM_USERS)

# # names
def generate_user_names():
    in_file = open(FNAME_FILENAME)
    first_names = in_file.read().splitlines()
    in_file.close()

    in_file = open(LNAME_FILENAME)
    last_names = in_file.read().splitlines()
    in_file.close()

    return [first_names[i] + ' ' + last_names[i] for i in uids]

user_names = generate_user_names()

# # join_dates
def generate_user_join_dates():
    dates = []
    current_date = MIN_DATE_JOINED
    for i in uids:
        current_date += timedelta(randrange(0, 4, 1), randrange(0, 86399 ,1), 0)
        dates.append(current_date)
    return dates

dates = generate_user_join_dates()

# # output insert intos
out_file = open(OUTPUT_FILENAME, 'w+')
for uid in uids:
    out_file.write('INSERT INTO User (uid, name, date_joined)' \
        + ' VALUES (\'' \
        + str(uid) + '\', \'' \
        + user_names[uid] + '\', \'' \
        + str(dates[uid]) + '\'' \
        + ');\n')
out_file.close()
