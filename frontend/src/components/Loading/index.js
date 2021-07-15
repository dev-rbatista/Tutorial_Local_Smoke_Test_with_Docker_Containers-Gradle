import React from 'react'
import {Puff, useLoading} from "@agney/react-loading";
import {LoadingDiv, LoadingInnerDiv} from "./LoadingElements";

function Loading() {
    const {containerProps, indicatorEl} = useLoading({
        loading: true,
        indicator: <Puff width="100"/>,
    });

    //https://reactjsexample.com/simple-and-accessible-loading-indicators-with-react/
    return (
        <>
            <LoadingDiv>
                <LoadingInnerDiv>
                    {/* Accessibility props injected to container */}
                    <section {...containerProps}>
                        {indicatorEl} {/* renders only while loading */}
                    </section>
                </LoadingInnerDiv>
            </LoadingDiv>
        </>
    )
}

export default Loading;