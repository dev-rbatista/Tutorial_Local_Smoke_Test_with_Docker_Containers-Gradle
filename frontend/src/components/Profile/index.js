import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../../context/AppContext';
import {addEmailToFamilyMember, fetchProfile, postNewRelation, deleteEmail} from "../../context/Actions";
import Loading from "../Loading";
import '../../styles/profile.css';
import '../../styles/global.css';

function Profile() {
    const {state, dispatch} = useContext(AppContext);
    const {loggedUser, profile, jwt} = state;
    const {id} = loggedUser;
    const {loading, error, profileData} = profile;
    const [email, setEmail] = useState("");
    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        fetchProfile(dispatch, id, jwt.jwt);
        setRefresh(false);
    }, [refresh]);

    useEffect(() => {
        fetchProfile(dispatch, id, jwt.jwt);

    }, []);


    function addEmails() {
        addEmailToFamilyMember(dispatch, id, email, jwt.jwt);
    }

    function handleSubmit() {
        if (email == null || email === "") {
            setRefresh(false);
        } else {
            addEmails();
            setRefresh(true);
        }

    }

    function handleDelete(email) {
        deleteEmail(dispatch, email, jwt.jwt);
        console.log(email);
    }


    function EmailsList() {
        const emailList = profileData.emails.map((email, index) => <div className="font-class button-grid">
            <p className="info">{index} {email}</p>
            <div className="button-profile-alignment">
                <button className="button-one-small button-profile-alignment" onClick={({email}) => handleDelete({email})}>delete
                </button>
            </div>
        </div>)
        return <div>{emailList}</div>
    }

    if (loading === true) {
        return (
            <h1><Loading/></h1>
        );
    } else {
        if (error !== null) {
            return (
                <div>
                    <h1>Error ....</h1>
                </div>
            );
        } else {
            /*  if (data.length > 0) {*/

            return (

                <div className="card">

                    <h1>{profileData.name}</h1>

                    {/*<p className="title">Family id : {profileData.familyID}</p>*/}

                    <div className="row-divider">

                    </div>

                    <div className="card-body">
                        <div className="row">
                            <div className="column-left">
                                <p className="item">Birth date: </p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.birthdate}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">Emails :</p>
                            </div>
                            <div className="column-right">
                                <div>
                                    {EmailsList()}
                                    <div className="button-grid">
                                        <input className="input-email" type="text" id="email"
                                               onChange={email => setEmail(email.target.value)} required/>
                                        <div className="button-profile-alignment">
                                            <button className="button-one-small" onClick={handleSubmit}>Add email
                                            </button>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">Phone number :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.phoneNumbers}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">VAT :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.vat}</p>
                            </div>
                        </div>

                        <div className="row">
                            <p className="address-title">Address </p>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">City :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.city}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">Street :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.street}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">Door number :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.doorNumber}</p>
                            </div>
                        </div>

                        <div className="row">
                            <div className="column-left">
                                <p className="item">Zip code :</p>
                            </div>
                            <div className="column-right">
                                <p className="info">{profileData.zipCode}</p>
                            </div>
                        </div>


                    </div>

                </div>


            );
            /*} else {
                return (<h1>No data ....</h1>);
            }*/
        }
    }

}

export default Profile;