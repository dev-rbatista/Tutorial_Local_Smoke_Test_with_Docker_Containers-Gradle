import React, {useContext} from 'react';
import {NavbarBodyButtonStyle, NavbarBodyDiv} from "./NavBarElements";
import '../../styles/navbarBody.css'
import AppContext from "../../context/AppContext";
import NavbarBodyButton from "./NavbarBodyButton";
import {FETCH_PROFILE_STARTED} from "../../context/Actions";
import {Redirect} from "react-router-dom";

function NavbarBody() {

    const { state, dispatch } = useContext(AppContext);
    const { loggedUser } = state;
    const { role } = loggedUser;


    switch (role) {
        case 'systemManager':
            return (
                <NavbarBodyDiv>
                    <NavbarBodyButton className="systemManager-1" valor="createFamily" description="Create a Family"/>
                </NavbarBodyDiv>
            )
        case 'familyAdministrator':
            return (
                <NavbarBodyDiv>
                    <NavbarBodyButton className="familyAdmin-1" valor="family" description="Family"/>
                    <NavbarBodyButton className="familyAdmin-2" valor="profile" description="Profile"/>
                </NavbarBodyDiv>
            )
        case 'familyMember':
            return (
                <NavbarBodyDiv>
                    <NavbarBodyButton className="familyMember-1" valor="profile" description="Profile"/>
                </NavbarBodyDiv>
            )
        default:
            return (<Redirect to="/login"/>)
    }

}

export default NavbarBody;