
# Homework 1-Building on Books API: Advanced Endpoints & Features

## Name: Bryan Lopez Leiva

## Features: 

- Implemented:
  - implemented endpoints PUT, PATCH, DELETE, GET with pagination, and Advanced with filtering, sorting, and pagination combined(in this order).
 
## Showcase

- Initial GET endpoint:
<img width="1342" height="845" alt="Screenshot 2026-02-27 at 21 34 04" src="https://github.com/user-attachments/assets/c7369561-3263-483b-9ab5-a7cb26758bc6" />

<br><br>

- PUT endpoint:
  - Updated the **title**, **author** and **price** on id=1

<img width="1340" height="576" alt="Screenshot 2026-02-27 at 21 37 41" src="https://github.com/user-attachments/assets/b1a4b19a-9d9d-46b4-8210-5727d3451720" />

  - Second example of PUT endpoint. Update for id=3, updating the **title**, **author** and **price**

<img width="1338" height="392" alt="Screenshot 2026-02-27 at 21 43 15" src="https://github.com/user-attachments/assets/f548ef6c-bf14-4ab2-82e9-94355133b110" />


<br><br>

- PATCH endpoint:
  - Update the **price** only for id=2

<img width="1341" height="575" alt="Screenshot 2026-02-27 at 21 48 12" src="https://github.com/user-attachments/assets/df9df91b-4404-4491-99da-5c8161314299" />

  - Second example of PATCH endpoint. Update for id=4, updating the **title** and **author**

<img width="1341" height="640" alt="Screenshot 2026-02-27 at 22 00 53" src="https://github.com/user-attachments/assets/897a79cd-5b15-4299-9c7b-7f1e39aff9c9" />


<br><br>

- DELETE endpoint:
  - DELETE endpoint used on id=2
  - GET endpoint on id=2:
 <img width="1340" height="437" alt="Screenshot 2026-02-27 at 22 06 37" src="https://github.com/user-attachments/assets/31768ad9-4e37-45c9-97a5-1c8e47497cf9" />
  - DELETE: 
<img width="1338" height="418" alt="Screenshot 2026-02-27 at 22 07 01" src="https://github.com/user-attachments/assets/6493c443-e8a7-402c-b773-f640ed66c77c" />

  - GET endpoint on books confirming id=2 is deleted:
<img width="1340" height="612" alt="Screenshot 2026-02-27 at 22 07 17" src="https://github.com/user-attachments/assets/35d4b874-2162-459e-8005-133077a9d3c8" />


<br><br>

- GET endpoint with pagination:
  - Having **page=0** and **size=2** which should have Java and Python

<img width="1340" height="517" alt="Screenshot 2026-02-27 at 22 07 55" src="https://github.com/user-attachments/assets/5e3df3e9-9886-4726-8fb2-38278083a44b" />

  - Second example GET endpoint with pagination having **page=1** and **size=2** which should have only one book

<img width="1342" height="422" alt="Screenshot 2026-02-27 at 22 08 32" src="https://github.com/user-attachments/assets/bedccc97-d0e0-4a8b-af73-e81ebc38da54" />


<br><br>

- Advanced endpoint with filtering, sorting and pagination:
  - Testing with a **minPrice=25**, **maxPrice=40**, **sortBy=author**, **order=asc**, **page=0**, and **size=10**

<img width="1345" height="403" alt="Screenshot 2026-02-27 at 22 12 51" src="https://github.com/user-attachments/assets/b4f5187a-c4b9-4926-9727-5faa4a792b06" />

  - Second example with same inputs but changing **minPrice=20**

    <img width="1340" height="524" alt="Screenshot 2026-02-27 at 22 15 01" src="https://github.com/user-attachments/assets/e63239fe-0ef9-4e16-aec7-3f7a5a841e8c" />

  
<br><br>
