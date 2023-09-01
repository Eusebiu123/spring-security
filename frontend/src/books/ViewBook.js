import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewBook() {
  const [book, setBook] = useState({
    title: "",
    price: Number,
    author: "",
  });

  const { id } = useParams();

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/book/${id}`);
    setBook(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Book Details</h2>

          <div className="card">
            <div className="card-header">
              Details of book id : {book.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Title: </b>
                  {book.title}
                </li>
                <li className="list-group-item">
                  <b>Price: </b>
                  {book.price}
                </li>
                <li className="list-group-item">
                  <b>Author: </b>
                  {book.author}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
