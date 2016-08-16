SHORT_NAME        ?= msg-worker

all:
	@echo "available targets:"
	@echo "  * package     - build and package jar file"
	@echo "  * deploy      - push app to pcf dev"

package:
	mvn clean compile assembly:single

deploy:
	cf target -o MaryKayCommunityApp -s development
	cf push msg-worker -p target/msg-worker-1.0-SNAPSHOT-jar-with-dependencies.jar