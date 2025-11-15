package com.jmunoz.sealedpersistence.sec02.persistence.repository;

import com.jmunoz.sealedpersistence.sec02.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Normalmente, crearíamos una interfaz de repositorio por cada tabla, pero
// como usamos el tipo abstracto Account, al guardar, automáticamente cogerá el tipo correcto,
// CheckingAccount o SavingsAccount.
// En caso de una búsqueda, lo que hará es un Union All.
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
