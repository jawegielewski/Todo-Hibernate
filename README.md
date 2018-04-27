TODO List – Java

Do uruchomienia programu potrzeba aktywnego serwera MySql nasłuchującego na porcie 3306, opcjonalnie serwer http. Należy utworzyć bazę danych teldat, a w niej tabelę task_table z kolumnami:
INT PRIMARY_KEY AUTO_INCREMENT task_id, 
TIMESTAMP task_data, 
VARCHAR task_info.

Program do ORM wykorzystuje framework Hibernate.


Program należy uruchomić i klikając odpowiedni przycisk dostaniemy do dyspozycji szereg funkcji:
•	‘l’ – wywołuje nową instancję klasy rozszerzającej Timer, aktualizuje co sekundę czas oraz sprawdza czy nadchodzą taski,
•	‘a’ – na podstawie wprowadzonych danych dodawane i utrwalane zostają nowe instancje tasków w bazie danych,
•	‘r’ – usuwa z bazy danych task o wskazanym id,
•	‘e’ – edytuje i utrwala taska o określonym id w bazie danych,
•	‘d’ – wyświetla listę tasków na określony dzień.


Do każdego zadania (wzorzec obserwator – publisher) przypisywana jest jedna instancja klasy Clock, wyznaczającej aktualny czas i obserwująca nadchodzące zadania (wzorzec obserwator – observer).
