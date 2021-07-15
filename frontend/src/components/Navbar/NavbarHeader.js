import React, {useContext} from 'react';
import logo from '../../assets/ape_3.png';
import '../../styles/global.css'
import {ImageProperties, NavbarHeaderDiv} from "./NavBarElements";
import AppContext from "../../context/AppContext";
import {changeView} from "../../context/Actions";


function NavbarHeader() {

    const { dispatch } = useContext(AppContext);

    function handleClick(value) {
        dispatch(changeView(value))
    }

    return(
        <NavbarHeaderDiv onClick={() => handleClick('')} >
            <ImageProperties className="logo" src={logo} className="Navbar-logo" alt="logo" />
        </NavbarHeaderDiv>
    )
}

export default NavbarHeader;