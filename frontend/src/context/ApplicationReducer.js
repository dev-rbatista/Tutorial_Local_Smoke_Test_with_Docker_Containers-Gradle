import {
    ADD_EMAIL_FAILURE,
    ADD_EMAIL_STARTED,
    ADD_EMAIL_SUCCESS,
    ADD_NEW_MEMBER_FAILURE,
    ADD_NEW_MEMBER_START,
    ADD_NEW_MEMBER_SUCCESS,
    ADD_RELATION_FAILURE,
    ADD_RELATION_STARTED,
    ADD_RELATION_SUCCESS,
    AUTHENTICATION_FAILURE,
    AUTHENTICATION_STARTED,
    AUTHENTICATION_SUCCESS,
    CHANGE_REFRESH,
    CHANGE_RELATION_FAILURE,
    CHANGE_RELATION_START,
    CHANGE_RELATION_SUCCESS,
    CHANGE_USER,
    CHANGE_VIEW,
    CHANGE_VIEW_RELATION,
    CREATE_FAMILY_FAILURE,
    CREATE_FAMILY_STARTED,
    CREATE_FAMILY_SUCCESS,
    FETCH_FAMILY_NAME_FAILURE,
    FETCH_FAMILY_NAME_STARTED,
    FETCH_FAMILY_NAME_SUCCESS,
    FETCH_FAMILYRELATIONS_FAILURE,
    FETCH_FAMILYRELATIONS_STARTED,
    FETCH_FAMILYRELATIONS_SUCCESS,
    FETCH_PROFILE_FAILURE,
    FETCH_PROFILE_STARTED,
    FETCH_PROFILE_SUCCESS,
    FETCH_USER_NAME_FAILURE,
    FETCH_USER_NAME_START,
    FETCH_USER_NAME_SUCCESS,
    LOADING_LANDING_PAGE_FALSE,
    LOGOUT,
    UPDATE_NAME,
    DELETE_EMAIL_STARTED, DELETE_EMAIL_FAILURE, DELETE_EMAIL_SUCCESS
} from './Actions'

function reducer(state, action) {
    switch (action.type) {
        /*
        case ACTION_HERE:
            return {
                ...state,
                {data object from AppProvider}: {
                    {variable 1}: x,
                    {variable 2}: y,
                }
            }

         */

        case AUTHENTICATION_STARTED:
            return {
                ...state,
                jwt: {
                    loading: true,
                    error: null,
                    jwt: null,
                }
            }

        case AUTHENTICATION_SUCCESS:
            return {
                ...state,
                jwt: {
                    loading: false,
                    error: null,
                    jwt: action.payload.data,
                },
                loggedUser: {
                    id: action.payload.id,
                    role: action.payload.role,
                    //name: '',
                }
            }

        case AUTHENTICATION_FAILURE:
            return {
                ...state,
                jwt: {
                    loading: false,
                    error: true,
                    jwt: null,
                }
            }

        case FETCH_PROFILE_STARTED:
            return {
                ...state,
                profile: {
                    loading: true,
                    error: null,
                    profileData: null,
                }
            }
        case FETCH_PROFILE_SUCCESS:
            return {
                ...state,
                profile: {
                    loading: false,
                    error: null,
                    profileData: action.payload.data
                }
            }
        case FETCH_PROFILE_FAILURE:
            return {
                ...state,
                profile: {
                    loading: false,
                    error: action.payload.error,
                    profileData: null,
                }
            }

        case UPDATE_NAME:
            let teste = action.payload.data;
            if (teste === '3') {
                teste = 'vai po crlh';
            }
            return {
                ...state,
                name: {
                    loading: false,
                    error: null,
                    data: teste,
                }
            }

        case CHANGE_USER:
            return {
                ...state,
                loggedUser: {
                    id: action.payload.id,
                    role: action.payload.role,
                }
            }

        case ADD_RELATION_STARTED:
            return {
                ...state,

            }

        case ADD_RELATION_FAILURE:
            return {
                ...state,
                family: {
                    loading: false,
                    error: true,
                    data: []
                }
            }

        case ADD_RELATION_SUCCESS:
            return {
                ...state,
                refresh: true,
            }

        case FETCH_FAMILYRELATIONS_STARTED:
            return {
                ...state,
                family: {
                    loading: true,
                    error: null,
                    data: {
                        familyMemberAndRelationsDTO: [{
                            name: "",
                            personID: "",
                            relations: [{
                                memberOneID: "",
                                memberTwoID: "",
                                relationDesignation: "",
                            }]
                        }],
                    }
                }
            }

        case FETCH_FAMILYRELATIONS_SUCCESS:
            return {
                ...state,
                family: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_FAMILYRELATIONS_FAILURE:
            return {
                ...state,
                family: {
                    loading: false,
                    error: action.payload.error,
                    data: {
                        familyMemberAndRelationsDTO: [{
                            name: "",
                            personID: "",
                            relations: [{
                                memberOneID: "",
                                memberTwoID: "",
                                relationDesignation: "",
                            }]
                        }]
                    }
                }
            }

        case
        FETCH_FAMILY_NAME_STARTED:
            return {
                ...state,
                familyData: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case
        FETCH_FAMILY_NAME_SUCCESS:
            return {
                ...state,
                familyData: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case
        FETCH_FAMILY_NAME_FAILURE:
            return {
                ...state,
                familyData: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }


        case
        LOGOUT:
            return {
                ...state,
                mainView: '',
                loggedUser: {
                    id: '',
                    role: '',
                    name: '',
                },
                landingPage: {
                    loading: true,
                    error: null,
                    name: '',
                    family_id: '',
                },
                refresh: false,
                name: {
                    loading: true,
                    error: null,
                    data: [],
                },
                loggeduserTest: {
                    email: ['tonyze@latinlover.com'],
                    familyId: ['@tonyze@latinlover.com'],
                },
                familyData: {
                    loading: true,
                    error: null,
                    data: [
                        {
                            familyName: '',
                            familyID: '',
                            adminID: '',
                            registrationDate: ''
                        }
                    ]
                },
                family: {
                    loading: true,
                    error: null,
                    data: {
                        familyMemberAndRelationsDTO: [{
                            name: "",
                            personID: "",
                            relations: [{
                                memberOneID: "",
                                memberTwoID: "",
                                relationDesignation: "",
                            }]
                        }],
                    },

                },
                addRelationStatus: null,
                familymembers: {
                    loading: true,
                    error: null,
                    data: [],
                    relations: [{
                        userid: 0,
                        data: [],
                    }],
                },
                profile: {
                    loading: true,
                    error: null,
                    profileData: {
                        id: "",
                        name: "",
                        birthdate: "",
                        emails: [],
                        phoneNumbers: [],
                        vat: "",
                        street: "",
                        city: "",
                        zipCode: "",
                        doorNumber: "",
                        familyID: "",
                    }
                },
                newMember: {
                    loading: true,
                    error: null,
                    newMemberData: {
                        adminID: '',
                        emailID: '',
                        name: '',
                        birthDate: '',
                        vatNumber: '',
                        phone: '',
                        street: '',
                        city: '',
                        houseNumber: '',
                        zipCode: '',
                    }
                },
                createdfamily: {
                    loading: true,
                    error: null,
                    data:
                        {
                            familyName: '',
                            familyID: '',
                            adminID: '',
                            registrationDate: ''
                        }

                }

            }

        case
        CHANGE_VIEW:
            return {
                ...state,
                mainView: action.payload.mainView,
            }

        case
        CHANGE_VIEW_RELATION:
            return {
                ...state,
                mainView: action.payload.mainView,
                relationID: action.payload.relationID,
            }


        case
        CHANGE_RELATION_START:
            return {
                ...state,
                loading: true,
                error: null,
            }

        case CHANGE_RELATION_SUCCESS:
            return {
                ...state,
                landingPage: {
                    loading: false,
                    error: null,
                }
            }

        case CHANGE_RELATION_FAILURE:
            return {
                ...state,
                landingPage: {
                    loading: false,
                    error: action.payload,
                }
            }


        case CHANGE_REFRESH:
            return {
                ...state,
                refresh: action.payload,
            }

        case FETCH_USER_NAME_START:
            return {
                ...state,
                landingPage: {
                    loading: true,
                    error: null,
                    name: '',
                    family_id: '',
                },
            }

        case
        FETCH_USER_NAME_SUCCESS:
            return {
                ...state,
                landingPage: {
                    loading: false,
                    error: null,
                    name: action.payload.name,
                    family_id: action.payload.family_id,
                }
            }

        case
        FETCH_USER_NAME_FAILURE:
            return {
                ...state,
                landingPage: {
                    loading: false,
                    error: action.payload,
                    name: '',
                }
            }

        case
        CREATE_FAMILY_STARTED:
            return {
                ...state,
                createdfamily: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case
        CREATE_FAMILY_SUCCESS:
            console.log(action)
            return {
                ...state,
                createdfamily: {
                    loading: false,
                    error: null,
                    data: {
                        familyName: action.payload.familyName,
                        familyID: action.payload.familyID,
                        adminID: action.payload.adminID,
                        registrationDate: action.payload.registrationDate
                    }
                }
            }

        case
        CREATE_FAMILY_FAILURE:
            return {
                ...state,
                createdfamily: {
                    loading: false,
                    error: action.payload,
                    data: []
                }
            }

        case
        ADD_EMAIL_STARTED:
            return {
                ...state,
                /*profile: {
                    loading: true,
                    error: null,

                }*/
            }

        case
        ADD_EMAIL_SUCCESS:
            return {
                ...state,
                /*profile: {
                    loading: false,
                    error: null,
                    profileData: []
                }*/
            }

        case
        ADD_EMAIL_FAILURE:
            return {
                ...state,
                landingPage: {
                    loading: false,
                    error: action.payload.error,
                    name: '',
                }
            }


        case
        ADD_NEW_MEMBER_START:
            return {
                ...state,
            }

        case ADD_NEW_MEMBER_FAILURE:

            return {
                ...state,
                newMember: {
                    loading: false,
                    error: action.payload,
                },
                family: {
                    loading: true,
                    error: null,
                    data: {
                        familyMemberAndRelationsDTO: [{
                            name: "",
                            personID: "",
                            relations: [{
                                memberOneID: "",
                                memberTwoID: "",
                                relationDesignation: "",
                            }]
                        }],
                    },

                },
            }

        case ADD_NEW_MEMBER_SUCCESS:

            return {
                ...state,
                refresh: true,
            }


        case LOADING_LANDING_PAGE_FALSE:
            return {
                ...state,
                landingPage: {
                    loading: false,
                }
            }

        case DELETE_EMAIL_STARTED:
            return{
                ...state,
                loading: true,
                error: null,
            }

        case DELETE_EMAIL_SUCCESS:
            return{
                ...state,
                refresh: true,
                /*
                profile: {
                    loading: false,
                    error: null,
                    profileData: {
                        emails: action.payload
                    }
                }

                 */
            }

        case DELETE_EMAIL_FAILURE:
            return {
                ...state,
                profile: {
                    loading: false,
                    error: action.payload
                }
            }

        default:
            return state;


    }
}

export default reducer;