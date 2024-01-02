all: stop_server run_servers run_client

compile_filtering:
	cd Train_Filtering/src/Filtering && javac -cp .:../../../restlet-jee-2.4.3/lib/org.restlet.jar:../../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../../DataBase/mysql-connector-j-8.1.0.jar:. *.java

run_servers: compile_filtering compile_booking deploy_train_booking

compile_booking: 
	cd Train_Filtering/src/ && java -cp .:../../restlet-jee-2.4.3/lib/org.restlet.jar:../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../DataBase/mysql-connector-j-8.1.0.jar Filtering.RESTDistributor &

deploy_train_booking:
	(source ./set-env.sh && cd ./Train_Booking && ant build)
	pwd
	cp Train_Booking/build/lib/BookingWS.aar axis2-1.5.1/repository/services/
	axis2-1.5.1/bin/axis2server.sh

run_client:
	cd WebPage && npm start

stop_server:
	@echo "Stopping process using port 8080..."
	@PID=$$(lsof -ti :8080); \
	if [ -n "$$PID" ]; then \
		echo "Killing process $$PID..."; \
		kill -9 $$PID; \
		echo "Process stopped."; \
	else \
		echo "No process found using port 8080."; \
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
