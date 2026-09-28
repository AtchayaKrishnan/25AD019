// ===============================
// DONATION FORM
// ===============================

const donationForm =
    document.getElementById("donationForm");


donationForm.addEventListener("submit", function(event) {

    event.preventDefault();


    const donationData = {

        donorName:
        document.getElementById("donorName").value,

        type:
        document.getElementById("type").value,

        category:
        document.getElementById("category").value,

        condition:
        document.getElementById("condition").value,

        ageGroup:
        document.getElementById("ageGroup").value,

        gender:
        document.getElementById("gender").value,

        quantity:
            Number(
                document.getElementById("quantity").value
            ),

        donationDate:
        document.getElementById("donationDate").value,

        driveId:
            Number(
                document.getElementById("driveId").value
            )
    };


    fetch("/api/donations", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(donationData)

    })

        .then(function(response) {

            if (!response.ok) {
                throw new Error("Donation could not be submitted");
            }

            return response.json();

        })

        .then(function(data) {

            const message =
                document.getElementById("message");

            message.textContent =
                "Donation submitted successfully!";

            message.style.color = "#6336a3";

            donationForm.reset();

        })

        .catch(function(error) {

            const message =
                document.getElementById("message");

            message.textContent =
                "Error submitting donation.";

            message.style.color = "#d9534f";

            console.error(error);

        });

});


// ===============================
// LOAD RECORDS
// ===============================

function loadDonations() {

    const donationList =
        document.getElementById("donationList");


    donationList.innerHTML =
        "<p>Loading records...</p>";


    Promise.all([

        fetch("/api/donations"),

        fetch("/api/recipients"),

        fetch("/api/distributions")

    ])

        .then(function(responses) {

            if (!responses[0].ok) {
                throw new Error("Donation API could not be loaded");
            }

            if (!responses[1].ok) {
                throw new Error("Recipient API could not be loaded");
            }

            if (!responses[2].ok) {
                throw new Error("Distribution API could not be loaded");
            }


            return Promise.all([

                responses[0].json(),

                responses[1].json(),

                responses[2].json()

            ]);

        })


        .then(function(data) {

            const donations = data[0];

            const recipients = data[1];

            const distributions = data[2];


            donationList.innerHTML = "";


            let totalDonated = 0;

            let totalDistributed = 0;


            // ===============================
            // DONATIONS
            // ===============================

            const donationHeading =
                document.createElement("h2");

            donationHeading.textContent =
                "Recent Donations";

            donationHeading.className =
                "record-heading";

            donationList.appendChild(
                donationHeading
            );


            donations.forEach(function(donation) {

                totalDonated +=
                    Number(donation.quantity);


                const card =
                    document.createElement("div");

                card.className =
                    "donation-card";


                card.innerHTML = `

                <h3>
                    ${donation.donorName}
                </h3>

                <p>
                    <strong>Type:</strong>
                    ${donation.type}
                </p>

                <p>
                    <strong>Category:</strong>
                    ${donation.category}
                </p>

                <p>
                    <strong>Condition:</strong>
                    ${donation.condition}
                </p>

                <p>
                    <strong>Age Group:</strong>
                    ${donation.ageGroup}
                </p>

                <p>
                    <strong>Gender:</strong>
                    ${donation.gender}
                </p>

                <p>
                    <strong>Quantity:</strong>
                    ${donation.quantity}
                </p>

                <p>
                    <strong>Date:</strong>
                    ${donation.donationDate}
                </p>

            `;


                donationList.appendChild(card);

            });


            // ===============================
            // RECIPIENTS
            // ===============================

            const recipientHeading =
                document.createElement("h2");

            recipientHeading.textContent =
                "Recipients";

            recipientHeading.className =
                "record-heading";

            donationList.appendChild(
                recipientHeading
            );


            if (distributions.length === 0) {

                const noData =
                    document.createElement("p");

                noData.textContent =
                    "No distributed donations yet.";

                noData.className =
                    "no-data";

                donationList.appendChild(noData);

            }


            distributions.forEach(
                function(distribution) {

                    totalDistributed +=
                        Number(
                            distribution.quantityReceived
                        );


                    const recipient =
                        recipients.find(
                            function(item) {

                                return Number(item.id) ===
                                    Number(
                                        distribution.recipientId
                                    );

                            }
                        );


                    const card =
                        document.createElement("div");

                    card.className =
                        "donation-card";


                    if (recipient) {

                        card.innerHTML = `

                        <h3>
                            ${recipient.organizationName}
                        </h3>

                        <p>
                            <strong>Contact Person:</strong>
                            ${recipient.contactPerson}
                        </p>

                        <p>
                            <strong>City:</strong>
                            ${recipient.city}
                        </p>

                        <p>
                            <strong>Required Items:</strong>
                            ${recipient.requiredItems}
                        </p>

                        <p>
                            <strong>Quantity Received:</strong>
                            ${distribution.quantityReceived}
                        </p>

                        <p>
                            <strong>Distribution Date:</strong>
                            ${distribution.distributionDate}
                        </p>

                    `;

                    } else {

                        card.innerHTML = `

                        <h3>
                            Recipient
                        </h3>

                        <p>
                            <strong>Recipient ID:</strong>
                            ${distribution.recipientId}
                        </p>

                        <p>
                            <strong>Quantity Received:</strong>
                            ${distribution.quantityReceived}
                        </p>

                        <p>
                            <strong>Distribution Date:</strong>
                            ${distribution.distributionDate}
                        </p>

                    `;

                    }


                    donationList.appendChild(card);

                }
            );


            // ===============================
            // SUMMARY
            // ===============================

            const remaining =
                totalDonated -
                totalDistributed;


            const summary =
                document.createElement("div");

            summary.className =
                "summary-card";


            summary.innerHTML = `

            <h2>
                Donation Summary
            </h2>

            <div class="summary-item">

                <span>
                    Total Donated
                </span>

                <strong>
                    ${totalDonated}
                </strong>

            </div>


            <div class="summary-item">

                <span>
                    Total Distributed
                </span>

                <strong>
                    ${totalDistributed}
                </strong>

            </div>


            <div class="summary-item">

                <span>
                    Remaining
                </span>

                <strong>
                    ${remaining}
                </strong>

            </div>

        `;


            donationList.appendChild(summary);

        })


        .catch(function(error) {

            console.error(
                "Frontend error:",
                error
            );


            donationList.innerHTML = `

            <div class="error-message">

                <p>
                    Unable to load records.
                </p>

                <small>
                    ${error.message}
                </small>

            </div>

        `;

        });

}