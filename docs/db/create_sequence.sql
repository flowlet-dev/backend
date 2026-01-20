create sequence flowlet.account_id_seq maxvalue 9999999999 cycle owned by flowlet.m_account.account_id;
create sequence flowlet.category_id_seq maxvalue 99999 cycle owned by flowlet.m_category.category_id;
create sequence flowlet.virtual_account_id_seq maxvalue 9999999999 cycle owned by flowlet.m_virtual_account.virtual_account_id;
create sequence flowlet.transaction_id_seq maxvalue 9999999999 cycle owned by flowlet.t_transaction.transaction_id;