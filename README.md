# route-traversal
A Spring-boot project that returns a list route bordering the provided origin and destination countries.

To test the project from POSTMAN;
_Clone the project, build and run._

**mvn spring-boot:run**

- set the _Context-Type_=_application/json_ on the header
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
