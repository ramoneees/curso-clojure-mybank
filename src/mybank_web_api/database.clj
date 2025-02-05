(ns mybank-web-api.database
  (:require [com.stuartsierra.component :as component]
            [datomic.client.api :as d]
            [datomic.dev-local]
            [clj-http.client :as client]))

"Nossa aplicação até o momento inicia nosso banco de dados em um simples atomo com
 um map clojure"

"Dessa forma a inicialização e encerramento dele deve ser controlada por nós.
Vamos definir nosso banco em um component, nesse exemplo simples ainda pouco vemos
a vantagem nisso, porém quando esse banco tiver uma conexão utilizada em muitos momentos
por todo o código fica mais claro e bem definido onde é o ponto que o banco é acessivel no
sistema."

"Essa store que o menino ta falando vc entendeu o que faz e pra que serve?"
"Aqui no meu exemplo o contas.edn é a mesma coisa, um map com chaves e valores, ai no
exemplo dele a chave é o uuid da tarefa, aqui é o numero da conta :1, :2 e :3.

O valor de cada chave no mapa dele é um outro mapa com o nome e status da tarefa.

No meu exemplo pra cada conta tem um mapa com a chave :saldo e o valor"

(defrecord Database [config]
  component/Lifecycle

  (start [this]
    (let [config (-> config :config) 
          client (d/client (select-keys config [:system :server-type]))
          _ (d/create-database client (select-keys config [:db-name]))
          conn (d/connect client (select-keys config [:db-name]))] 
      (assoc this :conn conn)))


  (stop [this]
    (println "Limpar as contas.edn da memória e Salvar em disco para uso futuro.")
    (assoc this :store nil)))

(defn new-database []
  (->Database {}))



