import {
    addInputedEmailToFamilyMember,
    addRelation,
    authenticateWS, changeRelation,
    createFamilySMService,
    familyNameGlobal,
    familyRelationsFA,
    fetchNameWS,
    fetchProfileFromLogin,
    fetchProfileFromWS,
    postNewMember,
    deleteEmailFMService
} from './Service'
import jwt_decode from "jwt-decode";

/**
 * Insert functions for Service:
 * - Success function
 * - Error function
 *
 * Both functions should have as ARG the dispatcher
 */


export const FETCH_PROFILE_STARTED = 'FETCH_PROFILE_STARTED';
export const FETCH_PROFILE_SUCCESS = 'FETCH_PROFILE_SUCCESS';
export const FETCH_PROFILE_FAILURE = 'FETCH_PROFILE_FAILURE';
export const DO_NOTHING = 'DO_NOTHING';
export const UPDATE_NAME = 'UPDATE_NAME';
export const CHANGE_USER = 'CHANGE_USER';
export const LOGOUT = 'LOGOUT';
export const CHANGE_VIEW = 'CHANGE_VIEW';
export const FETCH_FAMILYRELATIONS_STARTED = 'FETCH_FAMILYRELATIONS_STARTED';
export const FETCH_FAMILYRELATIONS_SUCCESS = 'FETCH_FAMILYRELATIONS_SUCCESS';
export const FETCH_FAMILYRELATIONS_FAILURE = 'FETCH_FAMILYRELATIONS_FAILURE';
export const FETCH_USER_NAME_START = "FETCH_USER_NAME_START";
export const FETCH_USER_NAME_SUCCESS = "FETCH_USER_NAME_SUCCESS";
export const FETCH_USER_NAME_FAILURE = "FETCH_USER_NAME_FAILURE";
export const LOADING_LANDING_PAGE_FALSE = "LOADING_LANDING_PAGE_FALSE";
export const CREATE_FAMILY_STARTED = "FETCH_USER_NAME_FAILURE";
export const CREATE_FAMILY_SUCCESS = "CREATE_FAMILY_SUCCESS";
export const CREATE_FAMILY_FAILURE = "CREATE_FAMILY_FAILURE";
export const ADD_EMAIL_STARTED = "ADD_EMAIL_STARTED";
export const ADD_EMAIL_SUCCESS = "ADD_EMAIL_SUCCESS";
export const ADD_EMAIL_FAILURE = "ADD_EMAIL_FAILURE";
export const CHANGE_REFRESH = "CHANGE_REFRESH";
export const AUTHENTICATION_STARTED = "AUTHENTICATION_STARTED";
export const AUTHENTICATION_SUCCESS = "AUTHENTICATION_SUCCESS";
export const AUTHENTICATION_FAILURE = "AUTHENTICATION_FAILURE";
export const DELETE_EMAIL_STARTED = "DELETE_EMAIL_STARTED";
export const DELETE_EMAIL_SUCCESS = "DELETE_EMAIL_SUCCESS";
export const DELETE_EMAIL_FAILURE = "DELETE_EMAIL_FAILURE";
export const CHANGE_VIEW_RELATION = "CHANGE_VIEW_RELATION";


// export const ADD_EMAIL = 'ADD_EMAIL';

export function doNothing() {
    return {
        type: DO_NOTHING,
        payload: {}
    }
}

export function authenticate(dispatch, userDetails) {
    dispatch(authenticateStarted());
    authenticateWS((res) => dispatch(authenticateSuccess(res)), (err) => dispatch(authenticateFailure(err.message)), userDetails);
}

export function authenticateStarted() {
    return {
        type: AUTHENTICATION_STARTED
    }
}

export function authenticateSuccess(jwt) {

    const decoded = jwt_decode(jwt.data.token);

    return {
        type: AUTHENTICATION_SUCCESS,
        payload: {
            data: jwt.data.token,
                id: decoded.sub,
                role: decoded.role
        }
    }
}

export function authenticateFailure(message) {
    return {
        type: AUTHENTICATION_FAILURE,
        payload: {
            error: message
        }
    }
}

export function updateName(variable) {
    return {
        type: UPDATE_NAME,
        payload: {
            data: variable
        }
    }
}

export function logout(dispatch) {
    return {
        type: LOGOUT,
    }
}

export function changeView(value) {
    return {
        type: CHANGE_VIEW,
        payload: {
            mainView: value
        }
    }
}

export function changeViewToEditRelation(value, relationID) {
    return {
        type: CHANGE_VIEW_RELATION,
        payload: {
            mainView: value,
            relationID: relationID
        }
    }
}

export function changeRefresh(value) {
    return {
        type: CHANGE_REFRESH,
        payload: value
    }
}

export function postNewRelation(dispatch, createRelationDTO, familyID, jwt) {
    dispatch(postNewRelationStarted());
    addRelation((res) => dispatch(addRelationSuccess(res)), (err) => dispatch(addRelationFailure(err.message)), createRelationDTO, familyID, jwt);
}

export const ADD_RELATION_FAILURE = 'ADD_RELATION_FAILURE';

export function addRelationFailure(message) {
    return {
        type: ADD_RELATION_FAILURE,
        payload: {
            error: message
        }
    }
}

export const ADD_RELATION_SUCCESS = 'ADD_RELATION_SUCCESS';

export function addRelationSuccess(success) {
    return {
        type: ADD_RELATION_SUCCESS,
        payload: {
            data: success
        }
    }
}


export const ADD_RELATION_STARTED = 'ADD_RELATION_STARTED';

export function postNewRelationStarted() {
    return {
        type: ADD_RELATION_STARTED,
    }
}


export function fetchProfile(dispatch, id, jwt) {
    dispatch(fetchProfileStarted());
    fetchProfileFromLogin((res) => dispatch(fetchProfileSuccess(res)), (err) => dispatch(fetchProfileFailure(err.message)), id, jwt);
}

export function fetchNewProfile(dispatch, id, jwt) {
    dispatch(fetchProfileStarted());
    fetchProfileFromWS((res) => dispatch(fetchProfileSuccess(res)), (err) => dispatch(fetchProfileFailure(err.message)), id, jwt);
}

// Uniformizar actions com pedidos fetch para poder utilizar com families, person etc...

export function fetchProfileStarted() {
    return {
        type: FETCH_PROFILE_STARTED,
    }
}

export function fetchProfileSuccess(profile) {
    return {
        type: FETCH_PROFILE_SUCCESS,
        payload: {
            data: profile
        }
    }
}

export function fetchProfileFailure(message) {
    return {
        type: FETCH_PROFILE_FAILURE,
        payload: {
            error: message
        }
    }
}


export const FETCH_FAMILY_NAME_STARTED = 'FETCH_FAMILY_NAME_STARTED';
export const FETCH_FAMILY_NAME_SUCCESS = 'FETCH_FAMILY_NAME_SUCCESS';
export const FETCH_FAMILY_NAME_FAILURE = 'FETCH_FAMILY_NAME_FAILURE';

export function fetchFamilyName(dispatch, familyId, jwt) {
    dispatch(fetchFamilyNameStarted());
    familyNameGlobal((res) => dispatch(fetchFamilyNameSuccess(res)), (err) => dispatch(fetchFamilyNameFailure(err.message)), familyId, jwt)
}

export function fetchFamilyRelationsFA(dispatch, familyId, jwt) {
    dispatch(fetchFamilyRelationStarted());
    familyRelationsFA((res) => dispatch(fetchFamilyRelationsSuccess(res)), (err) => dispatch(fetchFamilyRelationsFailure(err.message)), familyId, jwt)
}

/***** FAMILY *******/


export function fetchFamilyNameStarted() {
    return {
        type: FETCH_FAMILY_NAME_STARTED
    }
}


export function fetchFamilyNameFailure(message) {
    return {
        type: FETCH_FAMILY_NAME_FAILURE,
        payload: {
            data: message
        }
    }
}

export function fetchFamilyNameSuccess(familyRelations) {
    return {
        type: FETCH_FAMILYRELATIONS_SUCCESS,
        payload: {
            data: familyRelations
        }
    }
}

export function fetchFamilyRelationStarted() {
    return {
        type: FETCH_FAMILYRELATIONS_STARTED
    }
}


export function fetchFamilyRelationsSuccess(relations) {
    return {
        type: FETCH_FAMILYRELATIONS_SUCCESS,
        payload: {
            data: relations
        }
    }
}

export function fetchFamilyRelationsFailure(message) {
    return {
        type: FETCH_FAMILYRELATIONS_FAILURE,
        payload: {
            data: message
        }
    }
}

export function changeUser(email, role) {
    return {
        type: CHANGE_USER,
        payload: {
            id: email,
            role: role,
        },
    }
}

export function createFamilySM(dispatch, createFamily, jwt) {
    dispatch(createFamilySMStarted());
    createFamilySMService((res) => dispatch(createFamilySMSuccess(res)), (err) => dispatch(createFamilySMFailure(err.message)), createFamily, jwt);

}

export function createFamilySMStarted() {
    return {
        type: CREATE_FAMILY_STARTED
    }
}

export function createFamilySMSuccess(family) {
    return {
        type: CREATE_FAMILY_SUCCESS,
        payload: {
            familyName: family.data.familyName,
            familyID: family.data.familyID,
            adminID: family.data.adminID,
            registrationDate: family.data.registrationDate
        }
    }
}

export function createFamilySMFailure(errorMessage) {
    return {
        type: CREATE_FAMILY_FAILURE,
        payload: errorMessage
    }
}


/**
 * LandingPage
 */

export function fetchName(dispatch, id, jwt) {
    dispatch(fetchNameStart())
    fetchNameWS((res) => dispatch(fetchNameSuccess(res)), (err) => dispatch(fetchNameFailure(err.message)), id, jwt)
}

export function loadingLandingPageFalse() {
    return {
        type: LOADING_LANDING_PAGE_FALSE,
    }
}


export function fetchNameStart() {
    return {
        type: FETCH_USER_NAME_START,
    }
}

export function fetchNameSuccess(wsData) {
    return {
        type: FETCH_USER_NAME_SUCCESS,
        payload: {
            name: wsData.data.name,
            family_id: wsData.data.familyID
        }

    }
}

export function fetchNameFailure(error) {
    return {
        type: FETCH_USER_NAME_FAILURE,
        payload: error
    }
}

// Add email in Profile ----------------------------------------------------------------

export function addEmailToFamilyMember(dispatch, id, email, jwt) {
    dispatch(addEmailStarted());
    addInputedEmailToFamilyMember((res) => dispatch(addEmailSuccess(res)), (err) => dispatch(addEmailFailure(err.message)), id, email, jwt);

}

export function addEmailStarted() {
    return {
        type: ADD_EMAIL_STARTED,

    }
}

export function addEmailSuccess(email) {
    return {
        type: ADD_EMAIL_SUCCESS,
        payload: {
            email: email
        }


    }
}

export function addEmailFailure(error) {
    return {
        type: ADD_EMAIL_FAILURE,
        payload: error

    }
}

export const ADD_NEW_MEMBER_START = 'ADD_NEW_MEMBER_START'
export const ADD_NEW_MEMBER_SUCCESS = 'ADD_NEW_MEMBER_SUCCESS';
export const ADD_NEW_MEMBER_FAILURE = 'ADD_NEW_MEMBER_FAILURE';

export function addNewMember(dispatch, newMember, jwt) {
    dispatch(addNewMemberStart())
    postNewMember((response) => dispatch(addNewMemberSuccess(response)), (err) => dispatch(addNewMemberFailure(err.message)), newMember, jwt)

}

export function changeRelationAction(dispatch, newRelation, relationID, family_id, jwt) {
    dispatch(changeRelationStart())
    changeRelation((response) => dispatch(changeRelationSuccess(response)), (err) => dispatch(changeRelationFailure(err.message)), newRelation, relationID, family_id, jwt)

}

export const CHANGE_RELATION_START = 'CHANGE_RELATION_START';

export function changeRelationStart() {
    return {
        type: CHANGE_RELATION_START,
    }
}

export const CHANGE_RELATION_SUCCESS = 'CHANGE_RELATION_SUCCESS';

export function changeRelationSuccess(newMember) {
    return {
        type: CHANGE_RELATION_SUCCESS,
    }
}

export const CHANGE_RELATION_FAILURE = 'CHANGE_RELATION_FAILURE';

export function changeRelationFailure(error) {
    return {
        type: CHANGE_RELATION_FAILURE,
        payload: error
    }
}

export function addNewMemberStart() {
    return {
        type: ADD_NEW_MEMBER_START,
    }
}

export function addNewMemberSuccess(newMember) {
    return {
        type: ADD_NEW_MEMBER_SUCCESS,
    }
}

export function addNewMemberFailure(error) {
    return {
        type: ADD_NEW_MEMBER_FAILURE,
        payload: error
    }
}


export function deleteEmail(dispatch, email, jwt) {
    dispatch(deleteEmailStarted())
    deleteEmailFMService((response) => dispatch(deleteEmailSuccess(response)), (err) => dispatch(deleteEmailFailure(err.message)),email, jwt )}

export function deleteEmailStarted(){
    return{
        type: DELETE_EMAIL_STARTED,
    }
}
export function deleteEmailSuccess(outputRemoveEmailDTO){
    return{
        type: DELETE_EMAIL_SUCCESS,
        payload: outputRemoveEmailDTO
    }
}

export function deleteEmailFailure(error){
    return{
        type: DELETE_EMAIL_FAILURE,
        payload: error
    }
}

