package com.ct.commpanel.repository;

import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.commpanel.model.User;

@Repository
@ComponentScan( "com.ct.commpanel" )
public interface UserRepository extends JpaRepository< User, Long > {

	Optional< User > findById( long id );

	Optional< User > findByFirstName( String firstName );

	Boolean findByLastName( String lastName );

	Boolean existsByEmail( String email );
}
