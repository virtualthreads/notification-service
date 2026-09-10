INSERT INTO customer
(customer_code, name, email, phone_number, email_enabled, whatsapp_enabled)
VALUES
    ('CUST-1001','Rahul Sharma','rahul@gmail.com','+919876543210',TRUE,TRUE),
    ('CUST-1002','Priya Singh','priya@gmail.com','+919876543211',TRUE,TRUE),
    ('CUST-1003','Amit Kumar','amit@gmail.com','+919876543212',TRUE,TRUE),
    ('CUST-1004','Neha Verma','neha@gmail.com','+919876543213',TRUE,FALSE);

INSERT INTO notification_template
(template_code,event_type,description,active)
VALUES
    ('TPL-ORDER-PLACED','ORDER_PLACED','Order placed notification',TRUE),
    ('TPL-ORDER-SHIPPED','ORDER_SHIPPED','Order shipped notification',TRUE),
    ('TPL-ORDER-DELIVERED','ORDER_DELIVERED','Order delivered notification',TRUE),
    ('TPL-ORDER-CANCELLED','ORDER_CANCELLED','Order cancelled notification',TRUE);

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'EMAIL','Order {{orderId}} Confirmed',
       'Hi {{customerName}}, your order {{orderId}} has been successfully placed. Amount: {{currency}} {{orderAmount}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-PLACED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'WHATSAPP',NULL,
       'Hi {{customerName}}, your order {{orderId}} has been successfully placed. Amount: {{currency}} {{orderAmount}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-PLACED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'EMAIL','Your order {{orderId}} has shipped',
       'Hi {{customerName}}, your order {{orderId}} has shipped. Tracking number: {{trackingNumber}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-SHIPPED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'WHATSAPP',NULL,
       'Hi {{customerName}}, your order {{orderId}} has shipped. Tracking number: {{trackingNumber}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-SHIPPED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'EMAIL','Your order {{orderId}} has been delivered',
       'Hi {{customerName}}, your order {{orderId}} was delivered on {{deliveryDate}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-DELIVERED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'WHATSAPP',NULL,
       'Hi {{customerName}}, your order {{orderId}} was delivered on {{deliveryDate}}.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-DELIVERED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'EMAIL','Order {{orderId}} cancelled',
       'Hi {{customerName}}, your order {{orderId}} has been cancelled.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-CANCELLED';

INSERT INTO notification_template_channel (template_id,channel,subject,body,active)
SELECT id,'WHATSAPP',NULL,
       'Hi {{customerName}}, your order {{orderId}} has been cancelled.',TRUE
FROM notification_template WHERE template_code='TPL-ORDER-CANCELLED';