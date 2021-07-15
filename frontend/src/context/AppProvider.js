import React, {useReducer} from 'react';
// prop-types is an installed dependency
import PropTypes from "prop-types";
import {Provider} from './AppContext';
import reducer from './ApplicationReducer';

const initialState = {

    /*
    -> Insert data objects
    ex:
    users: {
        loading: true,
        error: null,
        data: [],
    },
    */

    mainView: '',

    relationID: '',

    jwt: {
        loading: false,
        error: null,
        jwt: null,
    },

    loggedUser: {
        id: '',
        role: '',
        name: '',
    },

    /*loggedUserProfile: {
        id: 'tonyze@latinlover.com',
    },*/

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
                    relationID: ""
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
};

console.log(initialState.profile);


// -> Insert more data objects in a different way
// ex:
const headers = {

    id: "ID",
    name: "Name",
    username: "User Name",
    vat: "VAT",
    birthdate: "Birthdate",
    city: "City",

};
/*
const labels = {

    one: "1",
    two: "2",
    three: "3",

}*/

const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            headers,
            dispatch
        }}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};


export default AppProvider;