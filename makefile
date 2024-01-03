all: stop_server run_servers run_client

run_filtering:
	cd Train_Filtering/src/Filtering && javac -cp .:../../../restlet-jee-2.4.3/lib/org.restlet.jar:../../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../../DataBase/mysql-connector-j-8.1.0.jar:. *.java

run_servers: run_filtering compile_booking deploy_train_booking

compile_booking: 
	cd Train_Filtering/src/ && java -cp .:../../restlet-jee-2.4.3/lib/org.restlet.jar:../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../DataBase/mysql-connector-j-8.1.0.jar Filtering.RESTDistributor &

deploy_train_booking:
	. set-env.sh
	javac -cp .:apache-tomcat-9.0.82/lib/servlet-api.jar:DataBase/mysql-connector-j-8.1.0.jar:axis2-1.5.1/lib/axis2-kernel-1.5.1.jar:axis2-1.5.1/lib/axiom-api-1.2.8.jar Train_Booking/*/*.java	
	cp -r Train_Booking axis2-1.5.1/repository/services
	axis2-1.5.1/bin/axis2server.sh 2>&1 &

run_client:
	cd WebPage && live-server --port=8081
	trap "make stop_server" EXIT; \

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
