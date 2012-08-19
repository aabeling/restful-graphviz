Start with
# mvn tomcat7:run

Test with curl:
# curl -o /tmp/digraph.svg -v -H "Content-Type: application/json" -X POST -d @/tmp/digraph.json http://localhost:9090/api/svg
