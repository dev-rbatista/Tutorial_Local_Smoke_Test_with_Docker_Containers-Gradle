import React, {useContext} from 'react';
import {NavbarFooterButton, NavbarFooterDiv} from "./NavBarElements";
import AppContext from "../../context/AppContext";
import '../../styles/global.css'
import '../../styles/navbarFooter.css'
import {logout} from "../../context/Actions";
import { useHistory } from "react-router-dom";


function NavbarFooter() {

    const { state, dispatch } = useContext(AppContext);
    const { loggedUser } = state;
    const { role } = loggedUser;
    const history = useHistory();

    function logoutRedirect() {
        let path = `/login`;
        history.push(path);
    }

    function handleClick() {
        dispatch(logout())
        logoutRedirect();
    }

    return (
        <NavbarFooterDiv>
            <NavbarFooterButton className="about button-one" onClick={() => alert("apes together strong \nGrupo 3")}>About</NavbarFooterButton>
                <br />
            <NavbarFooterButton className="contact button-one" onClick={() => alert("we contact you")}>Contact Us</NavbarFooterButton>
                <br />
            <NavbarFooterButton className="logout button-one-invert" onClick={handleClick}>Logout</NavbarFooterButton>
        </NavbarFooterDiv>
    )
}

export default NavbarFooter;