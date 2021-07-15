import React, {useContext} from 'react';

import Navbar from "../../components/Navbar";
import Mainview from "../../components/Mainview";
import {MainpageDiv} from "./MainpageElements";
import {useHistory} from "react-router-dom";
import AppContext from "../../context/AppContext";


function Mainpage() {

    return (
        <MainpageDiv>
            <Navbar />
            <Mainview/>
        </MainpageDiv>
    )
}

export default Mainpage;