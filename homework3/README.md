helm install homework3-db oci://registry-1.docker.io/bitnamicharts/postgresql --set global.postgresql.auth.username=test,global.postgresql.auth.password=password,global.postgresql.auth.database=homework3

helm install app ./Helm/

helm install prometheus -f ./prometheus/prometheus.yaml prometheus-community/kube-prometheus-stack

kubectl expose service prometheus-grafana --type=NodePort --target-port=3000 --name=prometheus-grafana-node-port

kubectl expose service prometheus-kube-prometheus-prometheus --type=NodePort --target-port=9090 --
name=prometheus-kube-prometheus-prometheus-node-port