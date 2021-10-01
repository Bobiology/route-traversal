# route-traversal
A Spring-boot project that returns a list of routes bordering the provided origin and destination countries.

**PROCESS FLOW DESIGN**

![image](https://user-images.githubusercontent.com/15633518/135681344-2ce5a8a6-227a-430f-8fba-cd76a50b528c.png)


**NB:** The initial call will be slower compared to the subsequent calls. During the initial API call, data is **fetched**, **transformed** and **loaded** to the **persistent queue** from which subsequent calls we be reading from, without necessarily calling the **3rd party data provider** endpoint.

**3rd Party Data Provider endpoint.**_ : ​https://raw.githubusercontent.com/mledoze/countries/master/countries.json

To test the project from POSTMAN

_Clone the project, build and run._

**Maven build-run command: ** _mvn spring-boot:run_

- Set the _Context-Type_=_application/json_ on the header
- Use basic authentication with **admin** username and **password** as password
- **Endpoint**: http://localhost:8084/routing/AFG/TKM
- **Method**: GET


**Configuration Screenshots**
![image](https://user-images.githubusercontent.com/15633518/135663025-eb653625-da18-4e78-957e-30d80db0b34d.png)
![image](https://user-images.githubusercontent.com/15633518/135663328-a3e81945-c009-465d-874c-81bb695d4ed8.png)

**Test Data**

CZE/ITA

**Sample Response Output**

![image](https://user-images.githubusercontent.com/15633518/135663397-85d2fa9c-5f40-49dc-bf5c-24fa7e34b16d.png)
![image](https://user-images.githubusercontent.com/15633518/135663521-0daa6c42-0ed7-4ca2-9ba8-299286eb30ba.png)

_To be included ..._
- CICD pipeline...
- Containerization...
