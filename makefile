all: stop_server run_servers run_client

compile_filtering:
	cd Train_Filtering/src/Filtering && javac -cp .:../../../restlet-jee-2.4.3/lib/org.restlet.jar:../../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../../DataBase/mysql-connector-j-8.1.0.jar:. -source 1.8 -target 1.8 *.java

run_servers: compile_filtering run_filtering run_booking

# cd Train_Filtering/src/ && java -cp .:../../restlet-jee-2.4.3/lib/org.restlet.jar:../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../DataBase/mysql-connector-j-8.1.0.jar Filtering.RESTDistributor &
run_filtering: 
	cd Train_Filtering/src/Filtering && javac -cp .:../../../restlet-jee-2.4.3/lib/org.restlet.jar:../../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../../DataBase/mysql-connector-j-8.1.0.jar:. -source 1.8 -target 1.8 *.java
	cd Train_Filtering/src/ && java -cp .:../../restlet-jee-2.4.3/lib/org.restlet.jar:../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../DataBase/mysql-connector-j-8.1.0.jar Filtering.RESTDistributor &

run_booking:
	cd Train_Booking && ant clean && ant generate.wsdl && ant generate.service
	cp Train_Booking/build/BookingWS.aar apache-tomcat-9.0.82/webapps/axis2/WEB-INF/services
	cd apache-tomcat-9.0.82/bin && ./startup.sh

run_docker: run_docker-config run_migration-data run_client

run_docker-config:
	docker-compose down
	docker-compose up --build -d

run_migration-data:
	sleep 2
	cd  DataBase && python3 migration.py   

run_client:
	sleep 2
	cd WebPage && flask run  --debugger --port=8081

stop_server:
	@echo "Stopping process using port 8088..."
	@PID=$$(lsof -ti :8088); \
	if [ -n "$$PID" ]; then \
		echo "Killing process $$PID..."; \
		kill -9 $$PID; \
		echo "Process stopped."; \
	else \
		echo "No process found using port 8088."; \
	fi

	@echo "Stopping process using port 8081..."
	@PID=$$(lsof -ti :8081); \
	if [ -n "$$PID" ]; then \
		echo "Killing process $$PID..."; \
		kill -9 $$PID; \
		echo "Process stopped."; \
	else \
		echo "No process found using port 8081."; \
	fi

	@echo "Stopping process using port 8182..."
	@PID_8182=$$(lsof -ti :8182); \
	if [ -n "$$PID_8182" ]; then \
		echo "Killing process $$PID_8182..."; \
		kill -9 $$PID_8182; \
		echo "Process on port 8182 stopped."; \
	else \
		echo "No process found using port 8182."; \
	fi
