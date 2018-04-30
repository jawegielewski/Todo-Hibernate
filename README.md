TODO List – Java

- Hibernate
- design pattern (observer)

To start program you need to have MySql server set up listening on port 3306 and optionally HTTP server running. Database 'todo_db' needs to be created and table 'task_table' with a specific columns inside it:

INT PRIMARY_KEY AUTO_INCREMENT task_id, <br />
TIMESTAMP task_data, <br />
VARCHAR task_info. <br />

ORM specification is used by Hibernate framework.

<br /><br /><br />


How to use.
Application when run waits for user to click the specific button on keyboard that is:

•	‘l’ – invokes new instance of class extending Timer, updates the time every second and verifies if any task comes, <br />
•	‘a’ – new instances of tasks in database are added and persisted based on user's input data, <br />
•	‘r’ – removes the task with specific id from database, <br />
•	‘e’ – edits and persists the task with specific id in database, <br />
•	‘d’ – displays the list of tasks for specific day. <br />


For every task (observer design pattern - publisher) there is one instance of Clock class assigned which defines current time and observes tasks comming (observer design pattern - observer).
