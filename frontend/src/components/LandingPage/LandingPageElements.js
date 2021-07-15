import styled from "styled-components";
import background from '../../assets/bg.png'

export const LandingPageDiv = styled.div`
  background: url(${background}) center center/cover no-repeat;
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: inset 0 0 0 1000px rgba(0,0,0, 0.2);
  object-fit: contain;
`

export const MainText = styled.text`
  color: var(--backgroundGrey);
  font-size: 48px;
  margin-top: -40px;
  margin-bottom: 40px;
  -webkit-text-stroke: 2px var(--backgroundBlack);
`

export const UserName = styled.text`
  color: var(--backgroundGrey);
  font-size: 72px;
  margin-top: 8px;
  font-weight: bolder;
  -webkit-text-stroke: 2px var(--backgroundBlack);
`