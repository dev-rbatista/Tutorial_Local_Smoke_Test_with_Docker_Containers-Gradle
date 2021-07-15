import React, {useContext, useEffect, useState} from 'react';
import {AddMemberDiv} from "./ChangeRelationElements";
import '../../styles/global.css'
import '../../styles/addMember.css'
import '../../styles/createFamily.css'
import {Form, Button, FormControl} from 'react-bootstrap';
import AppContext from "../../context/AppContext";
import {changeRelationAction, changeView} from "../../context/Actions";

function ChangeRelation() {
    const {state, dispatch} = useContext(AppContext);
    const {relationID, jwt, landingPage} = state;
    const {family_id} = landingPage;
    const [designation, setDesignation] = useState('');

    function handleClick(){
        const newRelation = {
            newRelationDesignation: designation,
        }

        changeRelationAction(dispatch, newRelation, relationID, family_id, jwt.jwt);
        dispatch(changeView('family'));

    }

    return(
        <AddMemberDiv>
            <h3 align="center">Change Relation</h3>
            <Form className="center-form">
                <Form.Group controlId="emailID">
                    <Form.Label>New Relation Description</Form.Label>
                    <br></br>
                    <Form.Control className="addMember-input" type="designation" placeholder="Enter new description" onChange={designation => setDesignation(designation.target.value)}/>
                </Form.Group>

                <div className="button-position">
                    <Button className="button-two-small" variant="primary" type="submit" onClick={handleClick}>Submit</Button>
                </div>
            </Form>

                </AddMemberDiv>
    )

}

export default ChangeRelation;