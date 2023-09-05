import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <div className="d-flex flex-column min-vh-50 bg-light">
      <div className="container flex-grow-1">
        <div className="row">
          <div className="col-md-4">
            <h5>Contact Us</h5>
            <address>
              123 Main Street<br />
              City, State 12345<br />
              Email: info@example.com<br />
              Phone: (123) 456-7890
            </address>
          </div>
          <div className="col-md-4">
            <h5>Quick Links</h5>
            <ul className="list-unstyled">
              <li><Link to="/addAccHolder">Add</Link></li>
              <li><Link to="/delAccHolder">Delete</Link></li>
              <li><Link to="/updAccHolder">Update</Link></li>
              <li><Link to="/searchAccHolder">Search</Link></li>
            </ul>
          </div>
          <div className="col-md-4">
            <h5>Follow Us</h5>
            <Link to="#" className="text-light mr-2"><i className="fab fa-facebook"></i></Link>
            <Link to="#" className="text-light mr-2"><i className="fab fa-twitter"></i></Link>
            <Link to="#" className="text-light mr-2"><i className="fab fa-linkedin"></i></Link>
            <Link to="#" className="text-light"><i className="fab fa-instagram"></i></Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Footer;
