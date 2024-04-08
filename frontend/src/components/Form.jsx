import { useState } from "react";
import axios from "axios";
import Swal from 'sweetalert2';

function Form() {
    const initialFormState = { name: "", email: "", message: "" };
    const [contactForm, setContactForm] = useState(initialFormState);
    const [errors, setErrors] = useState({});

    // Reset form fields after submission
    const resetForm = () => {
        setContactForm(initialFormState);
        document.querySelectorAll('input, textarea').forEach(input => input.value = '');
    };

    // Validate form fields to ensure they meet the required criteria
    const validateField = (name, value) => {
        switch (name) {
            case "name": {
                const isValidName = /^[a-zA-Z\s]*$/.test(value);
                const isValidLength = value.length >= 3 && value.length <= 50;
                return isValidName && isValidLength ? "" : "Name must contain only alphabetic characters and be between 3 and 50 characters";
            }
            case "message": {
                return value.length >= 10 && value.length <= 200 ? "" : "Message must be between 10 and 200 characters";
            }
            case "email": {
                // Regular expression to validate email format
                const isValidEmail = /\S+@\S+\.\S+/.test(value);
                return isValidEmail ? "" : "Invalid email format";
            }
            default:
                return "";
        }
    };

    // Handle form field changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setContactForm({ ...contactForm, [name]: value });
        const errorMessage = validateField(name, value);
        setErrors({ ...errors, [name]: errorMessage });
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        // Check if any form field has an error
        const hasErrors = Object.values(errors).some(err => err !== "");
        if (hasErrors) {
            Swal.fire({ icon: 'error', title: 'Oops...', text: 'Please fill out the form correctly' });
            return;
        }

        axios.post(`${import.meta.env.VITE_BASE_LOCAL}/submit-contact-form`, contactForm, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        })
        .then((res) => {
            Swal.fire({ icon: 'success', title: 'Success', text: res.data });
            resetForm();
        })
        .catch((err) => {
            Swal.fire({ icon: 'error', title: 'Oops...', text: err.response.data });
            resetForm();
        });
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col justify-center items-center shadow-xl mt-4 md:mt-5 lg:pt-5 lg:px-5 w-full">
            <div className="flex flex-col gap-y-5 lg:pt-4 pb-10 w-full">
                <div className="flex flex-col lg:text-lg">
                    <label htmlFor="name">Name<span className=" text-red-500">*</span></label>
                    <input className="p-1 lg:p-2 text-black" type="text" name="name" value={contactForm.name} onChange={handleChange} placeholder="Enter your name" required />
                    {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
                </div>
                <div className="flex flex-col lg:text-lg">
                    <label htmlFor="email">Email<span className=" text-red-500">*</span></label>
                    <input className="p-1 lg:p-2 text-black" type="email" name="email" value={contactForm.email} onChange={handleChange} placeholder="Enter your email" required />
                    {errors.email && <p className="text-red-500 text-sm mt-1">{errors.email}</p>}
                </div>
                <div className="flex flex-col lg:text-lg">
                    <label htmlFor="message">Message<span className=" text-red-500">*</span></label>
                    <textarea className="p-2 lg:p-4 text-black" name="message" value={contactForm.message} onChange={handleChange} placeholder="Enter your message" required></textarea>
                    {errors.message && <p className="text-red-500 text-sm mt-1">{errors.message}</p>}
                </div>
                <button className="p-2 bg-primary text-white hover:bg-slate-800" type="submit">Submit</button>
            </div>
        </form>
    );
}

export { Form };