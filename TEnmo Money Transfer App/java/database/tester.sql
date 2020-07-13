Begin Transaction;
Insert Into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)
values(2,2,2,1,100)
; 
Insert Into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)
values(2,2,1,2,100)
; 




Select * From transfers Where account_to IN(Select account_id From accounts Where user_id IN(Select user_id From users where user_id = 1))
OR account_from IN(Select account_id From accounts Where user_id IN(Select user_id From users where user_id = 1));

Select * From transfers Where account_to IN (Select account_id From accounts Where user_id IN (Select user_id From users where user_id = ?))
OR account_from IN (Select account_id From accounts Where user_id IN (Select user_id From users where user_id = ?));


Rollback;
Insert Into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)
values(2,2,4,1,100)
; 
select * from transfers; 


