```puml
autonumber
title Get Family Members List and Their Relations SD

participant ": IFamilyRESTController" as controller <<interface>>
participant ": IGetFamilyMembersAndRelationService" as service <<interface>>
participant "internalFamilyID: FamilyID" as familyID

participant ": IFamilyRepository" as familyrepo <<interface>>

participant "personList : List<<Person>>" as personList
participant "family : Family" as family

participant "relationList : List<Relation>" as relationList
participant "relation: Relation" as relation

participant ": IPersonRepository" as personrepo <<interface>>
participant "personList: List<Person>" as personList

participant "person : Person" as person


-> controller : GET /{familyID}/relation \ngetFamilyMembersAnd\nRelations(familyID)
activate controller
controller -> service : getFamilyMembersAnd\nRelations(familyID)
activate service
service -> familyID** :create(familyID)

service -> familyrepo : getByID(internalFamilyID)
activate familyrepo

ref over familyrepo

get family by id from persistence and create Value Objects (VOs)

end

familyrepo -> family** : create(VOs)

familyrepo -> relationList** : create()
loop for each relationJPA in relationJPAList
ref over familyrepo
create relation Value Objects (RVOs)
end
familyrepo -> relation** : create(RVOs)
familyrepo -> relationList : add(relation)

end

familyrepo -> family : setRelations(relationList)
return family

service -> personrepo : findAllByFamilyID(internalFamilyID)
activate personrepo

ref over personrepo

get personJPAList by family id from persistence

end

personrepo -> personList** : create()

loop for each personJPA in personJPAList
ref over personrepo 

Value Objects (VOs) for Person

end

personrepo -> person** : create(VOs)

personrepo -> personList : add(person)

end

return personList

ref over service

familyMembersAndRelationsDTOList = Get Family Members List and Their Relations SD 2.

end

return familyMembersAndRelationsDTOList

ref over controller

add links to familyMembersAndRelationsDTOList

end

return familyMembersAndRelationsDTOList 

```

```puml
title Get Family Members List and Their Relations SD 2.

autonumber 4

participant ": IGetFamilyMembersAndRelationService" as service <<interface>>
participant "person : Person" as person
participant "family : Family" as family

'participant ": PersonDTODomainAssembler" as persondomainsassembler <<interface>>
participant ": FamilyDTODomainAssembler" as familydomainsassembler <<interface>>
participant "familyMemberAndRelationsDTO \n: FamilyMemberAndRelationsDTO" as dto
participant "familyMembersAndRelationsDTOList \n: FamilyMembersAnd\nRelationsDTOList" as list

activate service


service -> list** : create()

loop for each person in personList

service -> person : getName().toString()
activate person 
return name

service -> person : getPersonId().toString()
activate person 
return personID

service -> dto**: create(personID, name)

service -> family : getRelationByPersonID(personID)
activate family 
return relations

service -> familydomainsassembler : createRelationOutputDTOList(relations)
activate familydomainsassembler
return relationsDTOList

service -> dto : setRelationsDTOList(relationsDTOList)

service -> list : add(FamilyMemberAndRelationsDTO)
end
deactivate service
```