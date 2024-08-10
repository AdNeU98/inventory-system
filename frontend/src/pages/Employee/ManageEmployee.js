// ManageEmployee.js

import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Container, Row, Button, Col, Table, Modal, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { URLS } from '../../routes';
import displayToast from '../../utils/displayToast';
import './ManageEmployee.css';

// import './manage-employees.css'; // Import manage-employees.css file

function ManageEmployee() {
  const [employees, setEmployees] = useState([]);
  const [currentEmployee, setCurrentEmployee] = useState(null);

  const [show, setShow] = useState(false);
  const handleClose = () => {
    setShow(false);
    setCurrentEmployee(null);
  };

  const handleShow = () => setShow(true);

  useEffect(() => {
    let isActive = true;

    if (isActive) {
      fetchEmployees();
    }
    return () => {
      isActive = false;
    };
  }, []);

  const fetchEmployees = async () => {
    const url = URLS.GET_ALL_EMPLOYEES;
    axios
      .get(url)
      .then(function (response) {
        // console.log(response);
        setEmployees(response.data);
      })
      .catch(function (error) {
        console.log(error);
        displayToast({ type: 'error', msg: 'Oops! Something went wrong' });
      });
  };

  const deleteEmployeeConfirmation = (b) => {
    setCurrentEmployee(b);
    handleShow();
  };

  const deleteEmployee = async () => {
    const url = URLS.DELETE_EMPLOYEE + currentEmployee.id;
    axios
      .delete(url)
      .then(function (response) {
        handleClose();
        displayToast({ type: 'success', msg: 'Employee deleted successfully!' });
        fetchEmployees();
      })
      .catch(function (error) {
        console.log(error);
        displayToast({ type: 'error', msg: 'Oops! Something went wrong' });
      });
  };

  return (
    
<Container className="container-main">
    <Row className="header-row">
      <Col md={6}>
        <h3>Employee List</h3>
      </Col>
      <Col md={6} className="add-product-col">
        <Link to="/add-employee">
          <Button variant="primary" className="add-product-button" >
          Add New Employee
          </Button>
        </Link>
		
        </Col>
        
      </Row>
      <Row>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Sr. No.</th>
              <th>Full Name</th>
              <th>Designation</th>
              {/* Add other table headers as needed */}
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {employees.map((employee, index) => {
              const { id, fullName, designation } = employee;

              return (
                <tr key={id}>
                  <td>{index + 1}</td>
                  <td>{fullName}</td>
                  <td>{designation}</td>
                  {/* Add other table cells as needed */}
                  <td>
                    <Link to={`/edit-employee/?id=${id}`}>
                      <Button variant="primary">Edit</Button>{' '}
                    </Link>
                    <Button onClick={() => deleteEmployeeConfirmation(employee)} variant="danger">
                      Delete
                    </Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </Row>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Delete Confirmation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to delete {currentEmployee ? currentEmployee.fullName : ''}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="danger" onClick={deleteEmployee}>
            Delete
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default ManageEmployee;
