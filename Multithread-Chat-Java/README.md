# Application de chat en Java

Pour que deux processus (par ex. un serveur & un client) communiquent ensemble, le plus simple est de créer, sur chacun des processus communiquants, un **socket**, qui joue le rôle d'interface entre les deux processus.

Un socket a un flux d'entrée et un flux de sortie accessibles via ses méthodes.

Dans le cas où davantage de processus doivent communiquent ensemble sur un même serveur, il faut également permettre aux processus client & serveur de créer des **threads**, c'est-à-dire des unités d'exécution d'instructions au sein d'un programme, qui ont l'avantage de demander moins de ressources aux machines qu'un programme. Si cette logique n'a pas un impact significatif sur les machines client, puisqu'elles exécutent chacune un programme pour se connecter au serveur, elle soulage grandement le serveur, qui peut tenir une liste de threads clients actifs, à laquelle il ajoute / supprime le thread au gré des connexions / déconnexions d'utilisateurs. 

En combinant les notions de *socket* et de *thread*, on peut créer, côté serveur, un processus qui initialise une liste de threads et exécute un socket unique. Ce socket accepte les connexions sur le port 6500, et transmet . 

Pour qu'une application de chat entre plusieurs clients fonctionnent, il faut faire tourner un serveur qui envoie, à chaque client, tous les messages qu'il reçoit de la part de l'ensemble des clients qui y sont connectés.



On ne peut pas faire tourner un programme serveur pour chaque client qui se connecte, car cela demanderait trop de ressources à la machine hôte du serveur.

On va donc faire tourner dans un unique programme serveur des *threads*, c'est-à-dire des programmes légers.
Ce programme serveur va écouter les connexions sur le port 6500 grâce à une boucle.
Quand un client lance le programme `client.Main`, celui-ci crée un thread client - instance de la classe `ThreadClient` - qu'il envoie au thread serveur sur le port 6500.
Dans sa boucle, le serveur reçoit le thread client, accepte sa connexion, et crée un thread serveur parallèle qu'il ajoute à sa liste de threads actifs.
Le thread client écoute les commandes saisies par l'utilisateur, et imprime `nom :  message` de l'utilisateur directement dans le flux de sortie du serveur.
Le thread serveur correspondant écoute son flux d'entrée, et en imprime une notification dans la console côté serveur, puis envoie le message traité 
qui saisira les messages entrés par l'utilisateur et les enverra vers le flux de sortie du thread serveur, situé sur le port 6500.

Lorsque le serveur reçoit un message

Pour chaque client qui se connecte, le programme serveur créera un thread qu'il ajoutera à la liste de ses threads actifs. 
**1 client = 1 thread**.

Chaque client pourra alors lancer le programme lui permettant de contacter le serveur sur le 



