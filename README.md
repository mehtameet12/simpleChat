**Simple Chat Application**

This is a simple chat interface that uses OCSF (Object Client/Server Framework) to show show the relay of messages between the client and the server.

To run the SimpleChat, 
- first install OCSF framework from the linkk bellow, and make sure ocsf is in your classpath (or is a subdirectory of simplechat1).

  Link to download OCSF:
  https://www.site.uottawa.ca/school/research/lloseng/supportMaterial/ocsf/ocsf.html#:~:text=To%20install%20OCSF%2C%20just%20unzip,file%20using%20your%20javac%20compiler.

- Then you must compile the .java files, including those in the subdirectories.

- Once complied, take the following steps:
  - First start a server: "java EchoServer". 
  - Start one or more clients: "java ClientConsole".

- To run a client on a different machine, you enter "java ClientConsole serveraddress", where serveraddress is the IP address or host name of the machine where the server is runing.
- Once a client is running, you can type some text; the text will be echoed to any other clients that are running.

If the server won't start, it might be because the default 'port' number of 5555 is already in use. 

Troubleshooting steps:
- Either kill the process running on port 555
- Or, use a different port to host it. 
