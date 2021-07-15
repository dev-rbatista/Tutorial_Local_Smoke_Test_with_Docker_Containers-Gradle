import styled from 'styled-components';

// Usando Styled Components, o naming desta styled.div não pode ser o mesmo do Componente
// em JS visto que no momento do import na classe correspondente não causa overflow

// Como a row só tem uma partição, apenas se coloca da "linha" 1 à 2
// Como as colunas são constituidas por duas divisões, a primeirpartição é da linha
// 1 à 2 enquanto que a restante vai ser definida no outro componente
export const NavbarDiv = styled.div`

  background-color: #52ec85;

  // Implementar o grid system no Parent através da construção do esqueleto e nos childs definir as dimensões de cada secção.
  display: grid;
  height: 100vh;
  
  // Neste situação o "fr" indica a totalidade da tela
  grid-template-columns: 1fr;
  grid-auto-rows: 1fr 2fr 1fr;
  
`

export const NavbarHeaderDiv = styled.div`
  justify-self: center;
  padding: 40px;
  max-width: 90%;
  text-align: center;
`

export const ImageProperties = styled.img`
`

export const NavbarBodyDiv = styled.div`
  justify-self: center;
  width: 80%;
  display: grid;
  grid-template-rows: 50px 50px 1fr;
  grid-row-gap: 10px;
`

export const NavbarFooterDiv = styled.div`
  justify-self: center;
  width: 80%;
  display: grid;
  grid-template-rows: 50px 50px 50px 1fr;
  grid-row-gap: 10px;
`

export const NavbarFooterButton = styled.button`

`

export const NavbarBodyButtonStyle = styled.div`
  justify-self: center;
`