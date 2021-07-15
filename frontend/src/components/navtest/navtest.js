import React, {useContext} from 'react';
import AppContext from "../../context/AppContext";
import {/* Actions */} from '../../context/Actions';


function navTest(){
    const {state, dispatch} = useContext(AppContext);

    function xxx(){
        const nothing = doNothing(); // Function from Actions
        dispatch(nothing);
    }

    return(
        // Insert HTML
        <div>

        </div>
    )
}

export default navTest;