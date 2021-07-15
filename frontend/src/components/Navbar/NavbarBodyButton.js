import React, {useContext} from 'react'
import {NavbarBodyButtonStyle} from "./NavBarElements";
import '../../styles/global.css'
import AppContext from "../../context/AppContext";
import {changeView} from "../../context/Actions";



function NavbarBodyButton(props) {

    const { dispatch } = useContext(AppContext);

    function handleClick(value) {
        dispatch(changeView(value))
    }

    return(
        <NavbarBodyButtonStyle className="button-one font-class" onClick={() => handleClick(props.valor)}>{props.description}</NavbarBodyButtonStyle>
    )
}

export default NavbarBodyButton;

/*
Adantes estava assim

function NavbarBodyFamilyButton() {

    const { dispatch } = useContext(AppContext);

    function handleClick(value) {
        dispatch(changeView(value))
    }

    return(
        <NavbarBodyButtonStyle onClick={() => handleClick('family')}>Family</NavbarBodyButtonStyle>
    )
}

export default NavbarBodyFamilyButton;

 */