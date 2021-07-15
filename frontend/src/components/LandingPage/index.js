import React, {useContext, useEffect} from 'react'
import {LandingPageDiv, MainText, UserName} from "./LandingPageElements";
import '../../styles/global.css';
import '../../styles/landingPage.css';
import AppContext from "../../context/AppContext";
import {fetchName, loadingLandingPageFalse} from "../../context/Actions";
import Loading from "../Loading";

function LandingPage() {

    const {state, dispatch} = useContext(AppContext);
    const {landingPage} = state;
    const {loggedUser,jwt} = state;
    const {jwt: token} = jwt;
    const {loading, name} = landingPage;

    useEffect(() => {
        /*if (name === '' /*&& loggedUser.role !== 'systemManager') {
            fetchName(dispatch, loggedUser.id, token)
        }*/
        fetchName(dispatch, loggedUser.id, token)
    }, [])

    if (loading === true) {
        return (
            <Loading/>
        )
    }

    return (
        <>
            <LandingPageDiv className="font-class">
                <MainText>
                    Welcome
                </MainText>
                <UserName>
                    {name}
                </UserName>
            </LandingPageDiv>
        </>
    )
}

export default LandingPage