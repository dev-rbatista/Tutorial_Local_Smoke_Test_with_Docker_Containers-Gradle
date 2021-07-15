import React, {useContext, useEffect, useState} from 'react';
import AppContext from "../../context/AppContext";
import '../../styles/global.css';
import '../../styles/memberRelations.css';
import {
    changeRefresh,
    changeView,
    changeViewToEditRelation,
    fetchFamilyRelationsFA,
    postNewRelation
} from "../../context/Actions";
import {Button, Table} from 'react-bootstrap';
import {MembersRelationsFADiv, RelationsList} from "./MembersRelationsFAElements";


function MembersRelationsFA() {

    const {state, dispatch} = useContext(AppContext);
    let {family, familyData, refresh, jwt} = state;
    const {familyName} = familyData;
    const {loading, error, data} = family
    const {familyMemberAndRelationsDTO} = data
    const {landingPage} = state;
    const [display, setDisplay] = useState(false)
    const [editRelation, setEditRelation] = useState(false)
    const {family_id} = landingPage;
    //const [relationID, setRelationID] = useState("")

    /*const [refreshVariable, setrefreshVariable] = useState(false);

    useEffect(() => {

        fetchFamilyRelationsFA(dispatch, family_id);
        setrefreshVariable(false);
    }, [refreshVariable]);*/


    useEffect(() => {

        fetchFamilyRelationsFA(dispatch, family_id, jwt.jwt)


    }, [])

    useEffect(() => {

        fetchFamilyRelationsFA(dispatch, family_id, jwt.jwt);
        dispatch(changeRefresh(false));
    }, [refresh])


    function findMemberTwoName(TwoID) {
        return familyMemberAndRelationsDTO.map((personDTO) => {
            if (TwoID === personDTO.personID) {
                return personDTO.name
            }
        })

    }


    function displayChange() {
        setDisplay(!display)
    }

    function addMemberRedirect(value) {
        dispatch(changeView(value))
    }

    function editRelationBegin(memberOneID, memberTwoID) {
        let relationID = findRelationID(memberOneID, memberTwoID);
        dispatch(changeViewToEditRelation("changeRelation", relationID))
    }


    function displayRole(index) {
        let tableRole = '';
        if (index === 0) {
            tableRole = 'Family Administrator';
        } else {
            tableRole = 'Family Member';
        }
        return tableRole;
    }


    function findRelationID(memberOneID, memberTwoID) {
        let returnValue = '';
        familyMemberAndRelationsDTO.map((personDTO) => {
            if (memberOneID === personDTO.personID) {
                personDTO.relations.map((relation) => {
                    if (relation.memberTwoID == memberTwoID) {
                        returnValue = relation.relationID;
                    }
                })
            }
        })
        return returnValue;
    }


    function buildTable() {
        const dto = familyMemberAndRelationsDTO.map((row, index) => {
            const relations = row.relations.map((relationsRow, relationsIndex) => {
                return (
                    <div className="relation-row" key={relationsIndex}>
                        <td className="relation-row-1">{relationsRow.relationDesignation} of {findMemberTwoName(relationsRow.memberTwoID)}
                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
                            <button className="editButton"
                                    onClick={() => editRelationBegin(relationsRow.memberOneID, relationsRow.memberTwoID)}>
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                            </button>
                        </td>
                        <td></td>
                    </div>
                )
            })
            return (
                <tr className="table-row font-class" style={{padding: "30px"}} key={index}>
                    <td className="table-row-element-1">{row.name}</td>
                    <td className="table-row-element-2">{displayRole(index)}</td>
                    <td className="relationList"><RelationsList props={display}>{relations}</RelationsList></td>
                </tr>
            )
        })
        return (<div>{dto}</div>)
    }


    function populateSelection() {
        let html = familyMemberAndRelationsDTO.map((row, index) => {
            return (
                <option key={index} value={row.personID}>{row.name}</option>
            )
        })
        return html;
    }

    const [memberA, setMemberA] = useState('');
    const [memberB, setMemberB] = useState('');
    const [relationDesignation, setRelationDesignation] = useState('');

    function handleSubmit() {
        const newRelationDTO = {
            memberOneID: memberA,
            memberTwoID: memberB,
            relationDesignation: relationDesignation
        }

        postNewRelation(dispatch, newRelationDTO, family_id, jwt.jwt);
    }

    function reloadScreen() {
        dispatch(changeRefresh(true));
    }

    if (error !== null) {
        return (
            <div>
                <h1><b>Error:</b></h1>
                <h4>There can only be <b>One (1)</b> relation between <b>Two (2) different</b> Family Members </h4>
                <button className="button-two" onClick={reloadScreen}>Try again</button>
            </div>
        )
    } else {
        if (loading === true) {
            return (
                <div>
                    Loading...
                </div>

            )
        } else {
            return (
                <MembersRelationsFADiv>
                    <div className="relation-add-table">
                        <Table>
                            <tr className="table-header font-class">
                                <th className="table-header-element-1">NAME</th>
                                <th className="table-header-element-2">ROLE</th>
                            </tr>
                            {buildTable()}
                        </Table>
                    </div>
                    <div className="table-interaction font-class">
                        <Button className="check-relations button-two" variant="dark" onClick={displayChange}>Check
                            Relations</Button>
                        <Button className="add-member button-two" variant="dark"
                                onClick={() => addMemberRedirect('addMember')}>Add Member</Button>
                    </div>
                    <div className="relation-select-container font-class">
                        <label className="relation-label-header" htmlFor="memberA">Create Relation</label>
                        <div>
                            <select className="select-option button-three" name="memberA" id="memberA"
                                    onChange={memberA => setMemberA(memberA.target.value)}>
                                <option className="select-option">Select Member A</option>
                                {populateSelection()}
                            </select>
                        </div>
                        <div>
                            <label className="relation-label-text" htmlFor="relDesignation">Relation Designation</label>
                            <input className="relation-input" type="text" id="relDesignation" name="relDesignation"
                                   onChange={relationDesignation => setRelationDesignation(relationDesignation.target.value)}></input>
                        </div>
                        <div>
                            <select className="button-three" name="memberB" id="memberB"
                                    onChange={memberB => setMemberB(memberB.target.value)}>
                                <option className="select-option">Select Member B</option>
                                {populateSelection()}
                            </select>
                            <Button className="add-relation button-two" onClick={handleSubmit} variant="dark">Add
                                Relation</Button>
                        </div>
                    </div>

                </MembersRelationsFADiv>
            )
        }
    }
}


export default MembersRelationsFA;