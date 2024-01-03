all: stop_server run_servers run_client

run_filtering:
	cd Train_Filtering/src/Filtering && javac -cp .:../../../restlet-jee-2.4.3/lib/org.restlet.jar:../../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../../DataBase/mysql-connector-j-8.1.0.jar:. *.java

run_servers: run_filtering compile_booking

compile_booking: 
	cd Train_Filtering/src/ && java -cp .:../../restlet-jee-2.4.3/lib/org.restlet.jar:../../restlet-jee-2.4.3/lib/org.restlet.ext.servlet.jar:../../DataBase/mysql-connector-j-8.1.0.jar Filtering.RESTDistributor &

run_client:
	cd WebPage && live-server --port=8081
	trap "make stop_server" EXIT; \

stop_server:
	@echo "Stopping process using port 8182..."
	@PID_8182=$$(lsof -ti :8182); \
	if [ -n "$$PID_8182" ]; then \
		echo "Killing process $$PID_8182..."; \
		kill -9 $$PID_8182; \
		echo "Process on port 8182 stopped."; \
	else \
		echo "No process found using port 8182."; \
	fi
