import React from 'react';
import {NavbarDiv} from "./NavBarElements";
import NavbarHeader from "./NavbarHeader";
import NavbarBody from "./NavbarBody";
import NavbarFooter from "./NavbarFooter";

function Navbar(){
    return (
        <NavbarDiv>
            <NavbarHeader />
            <NavbarBody />
            <NavbarFooter />
        </NavbarDiv>
    )
}

export default Navbar;