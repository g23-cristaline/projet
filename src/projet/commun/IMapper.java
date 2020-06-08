package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Equipe;
import projet.data.Memo;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;
import projet.data.Responsable;
import projet.data.Service;
import projet.data.Utilisateur;


@Mapper
public interface IMapper {  
	
	Utilisateur update( @MappingTarget Utilisateur courant, Utilisateur compte  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );
	
	Equipe update (@MappingTarget Equipe target, Equipe source);
	Participant update(@MappingTarget Participant target,Participant source);

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Memo update( @MappingTarget Memo target, Memo source );

	Service update( @MappingTarget Service target, Service source );

     Mission update(@MappingTarget Mission courant, Mission mission);
     Responsable update (@MappingTarget Responsable courant, Responsable source);
	
}
