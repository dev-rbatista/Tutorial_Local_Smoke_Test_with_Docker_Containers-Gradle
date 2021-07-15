
```puml
autonumber
    title Get Standard Category Tree SSD
    actor "System Manager" as systemManager
    participant "System" as system

    activate systemManager
    systemManager -> system: get standard category tree
    activate system
    system --> systemManager: category tree
    deactivate system
```

```puml
autonumber
title Get Standard Category Tree SD

participant ": ICategoryRESTController" as controller <<interface>>
participant ": IGetStandardCategoryTreeService" as service <<interface>>
participant ": CategoryDTODomainAssembler" as domainsassembler <<interface>>
participant ": OutputCategoryDTO" as dto
participant "categoryTreeDTO : CategoryTreeDTO" as tree
participant ": ICategoryRepository" as repo <<interface>>
participant "categoryList : List<<Category>>" as catList
participant "category : Category" as category <<interface>>
participant ": ICategoryDataDomainAssembler" as dataassembler <<interface>>
participant ": ICategoryRepositoryJPA" as jparepo <<interface>>


-> controller : getStandardCategoryTree()
activate controller
controller -> service : getStandardCategoryTree()
activate service
service -> repo : getStandardCategories()
activate repo
repo -> jparepo : findAllByFamilyIDJPAIsNull()
activate jparepo
return standardCategoriesJPA

repo -> catList** : create()

loop for each categoryJPA in standardCategoriesJPA

ref over repo, dataassembler

create Value Objects (VOs) for Category

end

repo -> category** : create(VOs) 

repo -> catList : add(category)

end

return categoryList

service -> tree** : create()

loop for each category in categoryList

service -> domainsassembler : toDTO(category)
activate domainsassembler
ref over domainsassembler

convert Category atributes (categoryAtributes) to string

end
domainsassembler -> dto** : create(categoryAtributes)
return outputCategoryDTO


service -> tree : add(outputCategoryDTO)

end

return categoryTreeDTO

ref over controller

add links to categoryTreeDTO

end

return categoryTreeDTO
```

```puml
hide empty members

package interface.adapters{

package controller { 

interface ICategoryRESTController

class CategoryRESTController

} 

package implrepositories {
class CategoryRespository 

}
}

package usecase.services{

package application.services {
interface IGetStandardCategoryTreeService

class GetStandardCategoryTreeService
}

package irepositories{
interface ICategoryRepository
}
}

package domain {

package category {
interface Category
class StandardCategory
}
}

Category <|.- StandardCategory

ICategoryRESTController <|.- CategoryRESTController 

IGetStandardCategoryTreeService <|.- GetStandardCategoryTreeService

ICategoryRepository <|.u-- CategoryRespository

CategoryRESTController -.> IGetStandardCategoryTreeService

ICategoryRepository <.- GetStandardCategoryTreeService

GetStandardCategoryTreeService -.> Category

CategoryRespository -u.> CategoryRepositoryJPA 


```