import React, {useContext} from 'react';
import '../../styles/mainView.css'
import '../../styles/global.css'
import {MainviewDiv} from "./MainviewElements";
import MembersRelationsFA from "../MembersRelationsFA";
import AppContext from "../../context/AppContext";
import CreateFamilySM from "../CreateFamilySM";
import LandingPage from "../LandingPage";
import Profile from "../Profile";
import AddMember from "../AddMember";
import ChangeRelation from "../ChangeRelation";

function Mainview() {
    const {state, dispatch} = useContext(AppContext);
    const {name, mainView} = state;
    const {data} = name;

    if (mainView === 'profile') {
        return (
            <MainviewDiv>

                    <Profile/>


            </MainviewDiv>
        )
    } else if (mainView === 'createFamily') {
        return (
            <MainviewDiv>
                <h3 align="center">Create a Family and Set Administrator</h3>
                <CreateFamilySM />
            </MainviewDiv>
        )
    } else if (mainView === 'family') {
        return (
            <MainviewDiv>
                <MembersRelationsFA/>
            </MainviewDiv>
        )
    } else if (mainView === 'addMember'){
        return (
            <MainviewDiv>
                <AddMember/>
            </MainviewDiv>
        )
    } else if (mainView ==='changeRelation'){
        return (
            <MainviewDiv>
                <ChangeRelation/>
            </MainviewDiv>
        )
    }

    return (
        <MainviewDiv>
            <LandingPage/>
        </MainviewDiv>
    )

}

export default Mainview