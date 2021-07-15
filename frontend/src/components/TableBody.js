import React, { useContext} from 'react';
import AppContext from '../context/AppContext';

function TableBody() {
    const { state, dispatch} = useContext(AppContext);
    const {profile} = state;
    const {data} = profile;

    return(
        <p>{data}</p>
    )
}
export default TableBody;