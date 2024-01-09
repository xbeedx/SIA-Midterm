import mysql.connector

# Paramètres de connexion à MySQL
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '',
}

# Nom de la base de données à créer
database_name = 'SIA'

# Charger le script SQL pour créer la base de données
create_database_sql = f"CREATE DATABASE IF NOT EXISTS {database_name}"

# Fonction pour exécuter un script SQL à partir d'un fichier
def execute_sql_script(cursor, script_file):
    try:
        with open(script_file, 'r') as script:
            sql_statements = script.read().split(';')
            for sql_statement in sql_statements:
                if sql_statement.strip():
                    cursor.execute(sql_statement)
    except Exception as e:
        print(f"Erreur lors de l'exécution du script SQL {script_file}: {str(e)}")

try:
    # Connexion à MySQL
    connection = mysql.connector.connect(**db_config)
    cursor = connection.cursor()

    # Créer la base de données
    cursor.execute(create_database_sql)
    print(f"Base de données '{database_name}' créée avec succès.")

    # Utiliser la base de données
    cursor.execute(f"USE {database_name}")

    # Création des tables en utilisant le script createTables.sql
    execute_sql_script(cursor, 'createTables.sql')
    print("Tables créées avec succès.")

    # Insertion des stations en utilisant le script createStations.sql
    # execute_sql_script(cursor, 'createStations.sql')
    # print("Stations insérées avec succès.")

    # Valider les changements dans la base de données
    connection.commit()

except Exception as e:
    print(f"Erreur lors de l'exécution des scripts SQL : {str(e)}")

finally:
    # Fermer le curseur et la connexion
    if 'cursor' in locals() and cursor:
        cursor.close()
    if 'connection' in locals() and connection:
        connection.close()
