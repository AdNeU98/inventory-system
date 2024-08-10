import React, { useContext } from "react";
import SideNav, {
  Toggle,
  Nav,
  NavItem,
  NavIcon,
  NavText,
} from "@trendmicro/react-sidenav";
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import { useHistory, useLocation } from "react-router-dom";
import { AuthContext } from "../../context/Auth";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCoffee,
  faHome,
  faThermometer,
  faUser,
  faUserTie,
  faFileInvoiceDollar,
  faUserTag,
  faReceipt,
  faChartPie,
  faCartPlus,
  faCarAlt,
  faCubes,
  faUserFriends,
  faAddressBook,
  faShoppingCart,
  faBoxOpen,
  faPeopleCarry,
  faFileInvoice,
  faMoneyBill,
  faBullseye,
  faCube,
} from "@fortawesome/free-solid-svg-icons";
import displayToast from "../../utils/displayToast";
import { Button, Navbar, Container } from "react-bootstrap";


function Sidenav() {
  const history = useHistory();
  const location = useLocation();
  const { isLoggedIn, setUserData, userData } = useContext(AuthContext);

  const logoutUser = () => {
    displayToast("Logged out successfully!", "success");

    setTimeout(() => {
      setUserData(null);
      history.push("/");
    }, 1000);
  };

  if (!isLoggedIn) {
    return null;
  } else {
    return (
      <React.Fragment>
        <Navbar>
          <Container>
          <Navbar.Brand
            href="/"
            style={{
              fontFamily: 'Your Custom Font', // Add your custom font-family
              fontSize: '24px', // Adjust the font size as needed
              fontWeight: 'bold', // Set the font weight to bold if desired
              color: '#333', // Set the color as needed
              // Add any other styling you'd like
            }}
          >Inventory Control System</Navbar.Brand>
            <Navbar.Collapse className="justify-content-end">
              <Navbar.Text>
                <FontAwesomeIcon
                    icon={faUserTie}
                    style={{
                      fontSize: '1.5rem', // Larger icon size
                      marginRight: '8px', // Space between icon and text
                      verticalAlign: 'middle' // Aligns icon with text
                    }}
                />
                <span style={{
                  verticalAlign: 'middle', // Aligns text with icon
                  fontSize: '1rem', // Font size for the user info, adjust as needed
                  fontWeight: 'bold', // Optional: if you want the name and designation to be bold
                  color: '#fff' // Color of the text
                }}>
      {userData.fullName} - <b>{userData.designation}</b>
    </span>
              </Navbar.Text>
            </Navbar.Collapse>


            <Navbar.Collapse className="justify-content-end">
              <Button variant="outline-dark" className="" onClick={logoutUser}>
                Logout
              </Button>
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <SideNav
          className="side--nav"
          onSelect={(selected) => {
            const to = "/" + selected;
            if (location.pathname !== to) {
              history.push(to);
            }
          }}
        >
          <SideNav.Toggle />
          <SideNav.Nav defaultSelected="home">
            {/* <NavItem eventKey="/home">
                        <NavIcon>
                        <FontAwesomeIcon icon={faHome} className="side-nav-icons"/>
                        </NavIcon>
                        <NavText>
                            Home
                        </NavText>
                    </NavItem> */}
            <NavItem eventKey="manage-products">
              <NavIcon>
                <FontAwesomeIcon icon={faCubes} className="side-nav-icons" />
                {/* <i className="fa fa-fw fa-device" style={{ fontSize: '1.75em' }} /> */}
              </NavIcon>
              <NavText>Add Products</NavText>
            </NavItem>

            {userData.designation.toUpperCase() === "MANAGER" && (
              <NavItem eventKey="manage-employees">
                <NavIcon>
                  <FontAwesomeIcon
                    icon={faUserTie}
                    className="side-nav-icons"
                  />
                  {/* <i className="fa fa-fw fa-device" style={{ fontSize: '1.75em' }} /> */}
                </NavIcon>
                <NavText>Add Employees</NavText>
              </NavItem>
            )}

            <NavItem eventKey="manage-buyers">
              <NavIcon>
                <FontAwesomeIcon
                  icon={faUserFriends}
                  className="side-nav-icons"
                />
                {/* <i className="fa fa-fw fa-device" style={{ fontSize: '1.75em' }} /> */}
              </NavIcon>
              <NavText>View Buyers</NavText>
            </NavItem>

            <NavItem eventKey="manage-purchase-order">
              <NavIcon>
                <FontAwesomeIcon
                  icon={faShoppingCart}
                  className="side-nav-icons"
                />
                {/* <i className="fa fa-fw fa-device" style={{ fontSize: '1.75em' }} /> */}
              </NavIcon>
              <NavText>Purchase Orders</NavText>
            </NavItem>

            <NavItem eventKey="manage-invoice">
              <NavIcon>
                <FontAwesomeIcon icon={faFileInvoiceDollar} className="side-nav-icons" />
                {/* <i className="fa fa-fw fa-device" style={{ fontSize: '1.75em' }} /> */}
              </NavIcon>
              <NavText>View Invoice</NavText>
            </NavItem>
          </SideNav.Nav>
        </SideNav>
      </React.Fragment>
    );
  }
}

export default Sidenav;
