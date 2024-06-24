DC_NAME=docker-compose.yml

UP_AUTOREFRESH_FLAG=--force-recreate # Эта команда заставит Docker Compose остановить и удалить все существующие контейнеры, а затем пересоздать и запустить их заново.
# используется для принудительного пересоздания контейнеров, даже если конфигурация или образы не изменились
PGDATA_LIB=pgdata/

up:
	docker-compose -f $(DC_NAME) up

rm:
	docker-compose stop \
	&& docker-compose rm \
	&& sudo rm -rf $(PGDATA_LIB)