import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";
import '../../style/register.css'


const RegisterPage = () => {

    const [formData, setFormData] = useState({
        email: '',
        name: '',
        password: '',
		address: {
			mobile: ''
		},
    });

    const [message, setMessage] = useState(null);
    const navigate = useNavigate();


    const handleChange = (e) => {
        const { name, value } = e.target;
		if(name === 'mobile'){
			setFormData({...formData,address: {...formData.address,mobile: value}});
		}else{
        setFormData({ ...formData, [name]: value });
		}
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
			// console.log(formData); status=SUCCESS
            const response = await ApiService.registerUser(formData);
			console.log("response::::"+response.status);
            if (response.status === 'SUCCESS') {
                setMessage("User Successfully Registerd");
                setTimeout(() => {
                    navigate("/login")
                }, 4000)
            }
        } catch (error) {
            setMessage(error.response?.data.message || error.message || "unable to register a user");
        }
    }

    return (
        <div className="register-page">
            <h2>Register</h2>
            {message && <p className="message">{message}</p>}
            <form onSubmit={handleSubmit}>
                <label>Email: </label>
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required />

                <label>Name: </label>
                <input
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required />


                <label>Phone Number: </label>
                <input
                    type="text"
                    name="mobile"
                    value={formData.address.mobile}
                    onChange={handleChange}
                    required />

                <label>Password: </label>
                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required />

                    <button type="submit">Register</button>
                    <p className="register-link">
                        Already have an account? <a href="/login">Login</a>
                    </p>
            </form>
        </div>
    )
}

export default RegisterPage;